package com.vs.twitterspringkafkastreams.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
    private String filteredBy;
    private String text;

    /*private String createdAt;
    private Double id;
    private String id_str;
    private String text;
    private String source;
    private Boolean truncated;
    private Double in_reply_to_status_id;
    private String in_reply_to_status_id_str;
    private Double in_reply_to_user_id;
    private String in_reply_to_user_id_str;
    private String in_reply_to_screen_name;
    private User user;
    private String coordinates;
    private String place; //Place
    private long quoted_status_id;
    private String quoted_status_id_str;
    private boolean is_quote_status;
    private Tweet quoted_status;
    private Tweet retweeted_status;
    private int quote_count;
    private int reply_count;
    private int retweet_count;
    private int favorite_count;
    private Entities entities;
    private Entities extended_entities;
    private boolean favorited;
    private boolean retweeted;
    private boolean possibly_sensitive;
    private String filter_level;
    private String lang;
    private String[] matching_rules; //Rule
*/
}
