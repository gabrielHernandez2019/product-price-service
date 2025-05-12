package com.zara.price.application.service;

import com.zara.price.domain.port.in.MessageHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaMessageService implements MessageHandler {
    @Override
    public void handleMessage(String message) {
        log.debug("message in {}", message);
    }
}
