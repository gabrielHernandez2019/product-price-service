package com.zara.price.application.config;


import com.zara.price.application.service.KafkaMessageService;
import com.zara.price.domain.port.in.MessageHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KafkaConfigTest {

    @Test
    void testMessageHandlerBean() {
        KafkaConfig config = new KafkaConfig();
        MessageHandler messageHandler = config.messageHandler();

        assertNotNull(messageHandler, "El bean MessageHandler no debe ser nulo");
        assertInstanceOf(KafkaMessageService.class, messageHandler, "El bean debe ser una instancia de KafkaMessageService");
    }
}
