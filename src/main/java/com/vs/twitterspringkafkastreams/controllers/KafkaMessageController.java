package com.vs.twitterspringkafkastreams.controllers;

import com.vs.twitterspringkafkastreams.producers.TwitterProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaMessageController {

    private final TwitterProducer twitterProducer;

    @GetMapping("/start")
    public void sendMessageToKafka() {
        twitterProducer.go();
    }
}
