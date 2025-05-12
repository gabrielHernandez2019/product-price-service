package com.zara.price.infrastructure.adapter.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerAdapter {
    private static final String TOPIC = "price-event";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerAdapter(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
