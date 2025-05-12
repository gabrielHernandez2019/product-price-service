package com.zara.price.application.config;

import com.zara.price.application.service.KafkaMessageService;
import com.zara.price.domain.port.in.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public MessageHandler messageHandler() {
        return new KafkaMessageService();
    }
}
