package com.vs.twitterspringkafkastreams.configs;

public class AppConfig {
    public final static String applicationID = "TwitterProducer";
    public final static String groupID = "TwitterValidatorGroup";
    public final static String bootstrapServers = "localhost:9092,localhost:9093";
    public final static String topicName = "twitter-two";
    public final static int frequencyInSec = 10;

}
