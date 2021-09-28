package com.vs.twitterspringkafkastreams;

import com.vs.twitterspringkafkastreams.producers.TwitterProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwitterSpringKafkaStreamsApplication {

    public static void main(String[] args) {

        SpringApplication.run(TwitterSpringKafkaStreamsApplication.class, args);
    }

}
