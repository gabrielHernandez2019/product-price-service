package com.zara.price.application.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class KafkaMessageServiceTest {

    @Test
    void testHandleMessageLogsMessage() {
        Logger mockLogger = mock(Logger.class);
        KafkaMessageService kafkaMessageService = new KafkaMessageService() {
            private final Logger log = mockLogger;

            @Override
            public void handleMessage(String message) {
                log.debug("message in {}", message);
            }
        };

        String testMessage = "Test message";
        kafkaMessageService.handleMessage(testMessage);
        verify(mockLogger).debug("message in {}", testMessage);
    }
}