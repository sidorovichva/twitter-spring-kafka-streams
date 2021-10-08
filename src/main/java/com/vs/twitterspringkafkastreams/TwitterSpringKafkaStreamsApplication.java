package com.vs.twitterspringkafkastreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwitterSpringKafkaStreamsApplication {

    public static void main(String[] args) {

        SpringApplication.run(TwitterSpringKafkaStreamsApplication.class, args);
    }

}
