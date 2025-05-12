package com.zara.price.infrastructure.adapter.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class KafkaProducerAdapterTest {

    @Test
    void testSendMessage() {
        KafkaTemplate<String, String> mockKafkaTemplate = mock(KafkaTemplate.class);
        KafkaProducerAdapter kafkaProducerAdapter = new KafkaProducerAdapter(mockKafkaTemplate);


        String testMessage = "Test message";
        kafkaProducerAdapter.sendMessage(testMessage);

        verify(mockKafkaTemplate).send("price-event", testMessage);
    }
}