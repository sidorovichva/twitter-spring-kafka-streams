package com.vs.twitterspringkafkastreams.serdes;

import com.vs.twitterspringkafkastreams.models.Tweet;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {
    static public final class TweetSerde extends WrapperSerde<Tweet> {
        public TweetSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    static public Serde<Tweet> Tweet() {
        TweetSerde serde =  new TweetSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Tweet.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
