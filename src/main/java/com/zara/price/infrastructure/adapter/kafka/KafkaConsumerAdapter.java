package com.zara.price.infrastructure.adapter.kafka;


import com.zara.price.domain.port.in.MessageHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerAdapter {

    private final MessageHandler messageHandler;

    public KafkaConsumerAdapter(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @KafkaListener(topics = "price-event", groupId = "1")
    public void consume(String message) {
        messageHandler.handleMessage(message);
    }
}

