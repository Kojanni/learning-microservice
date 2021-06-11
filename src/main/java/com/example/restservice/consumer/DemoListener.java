package com.example.restservice.consumer;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DemoListener {
    private final Logger LOG = LogManager.getLogger(this.getClass());

    @StreamListener(ConsumerChannels.DEMO)
    public void listener(String message) {
        LOG.info("Message of DemoListener: " + message);
    }
}
