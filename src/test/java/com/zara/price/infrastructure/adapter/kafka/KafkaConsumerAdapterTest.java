package com.zara.price.infrastructure.adapter.kafka;

import com.zara.price.domain.port.in.MessageHandler;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class KafkaConsumerAdapterTest {

    @Test
    void testConsumeCallsHandleMessage() {
        MessageHandler mockMessageHandler = mock(MessageHandler.class);
        KafkaConsumerAdapter kafkaConsumerAdapter = new KafkaConsumerAdapter(mockMessageHandler);
        String testMessage = "Test message";

        kafkaConsumerAdapter.consume(testMessage);

        verify(mockMessageHandler).handleMessage(testMessage);
    }
}