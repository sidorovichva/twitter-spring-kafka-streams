package com.vs.twitterspringkafkastreams.streams;

import com.vs.twitterspringkafkastreams.configs.AppConfig;
import com.vs.twitterspringkafkastreams.models.Tweet;
import com.vs.twitterspringkafkastreams.serdes.AppSerdes;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Log4j2
public class TwitterStreams {
    private static final int PAUSE = 2000;

    public TwitterStreams() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, AppSerdes.Tweet().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, Tweet> kStream = streamsBuilder.stream(AppConfig.topicName, Consumed.with(Serdes.String(), AppSerdes.Tweet()));
        kStream.foreach((k, v) -> System.out.println(k + " - " + v));

        Topology topology = streamsBuilder.build();
        KafkaStreams streams = new KafkaStreams(topology, props);
        streams.start();
    }
}
