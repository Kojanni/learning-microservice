package com.example.restservice.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannels {
    String DEMO = "demo";

    @Input(value = DEMO)
    SubscribableChannel listener();
}
