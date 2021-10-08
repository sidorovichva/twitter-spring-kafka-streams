package com.vs.twitterspringkafkastreams.streams;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import com.vs.twitterspringkafkastreams.configs.AppConfig;
import com.vs.twitterspringkafkastreams.configs.SecurityConfig;
import com.vs.twitterspringkafkastreams.models.Tweet;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class TwitterProducer {

    private final KafkaTemplate<String, Tweet> kafkaTemplate;
    private static final int PAUSE = 2000;
    private static final String TEXT = "/";
    private static long COUNTER = 1;

    private final BlockingQueue<String> msgQueue;
    private final Client client;
    private String msg;

    public TwitterProducer(KafkaTemplate<String, Tweet> kafkaTemplate) {
        log.info("Initialization...");
        this.kafkaTemplate = kafkaTemplate;
        msgQueue = new LinkedBlockingQueue<>(1);
        client = createTwitterClient(msgQueue);
        client.connect();
        msg = null;
    }

    private String getTextFromTweet(String tweet) {
        String start =  tweet.split("(,\"text\":\")")[1];
        return start.split("\",\"")[0];
    }

    @Scheduled(fixedRate = PAUSE)
    public void fetch() {
        try {
            log.info("trying to get a tweet...");
            msg = msgQueue.poll(AppConfig.frequencyInSec, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            client.stop();
        }
        if (msg != null) {
            log.info("sending a new tweet...");
            Tweet tweet = new Tweet(TEXT, getTextFromTweet(msg));
            kafkaTemplate.send(AppConfig.topicName, COUNTER + "-" + TEXT, tweet);
            COUNTER += 1;
        } else log.info("no tweet found");
        log.info("End of cycle. Sleep for " + PAUSE + " ms");
    }

    public Client createTwitterClient(BlockingQueue<String> msgQueue) {
        Hosts host = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
        List<String> terms = List.of(TEXT);
        endpoint.trackTerms(terms);

        Authentication auth = new OAuth1(
                SecurityConfig.consumerKey,
                SecurityConfig.consumerSecret,
                SecurityConfig.token,
                SecurityConfig.secret
        );

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")                              // optional: mainly for the logs
                .hosts(host)
                .authentication(auth)
                .endpoint(endpoint)
                .processor(new StringDelimitedProcessor(msgQueue));

        return builder.build();
    }
}
