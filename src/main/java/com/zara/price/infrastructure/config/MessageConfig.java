package com.zara.price.infrastructure.config;


import com.zara.price.infrastructure.exception.TechnicalException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "messages")
public class MessageConfig {

    private Map<String, String> errors;

    public String getErrorMessage(String key) {
        if (errors == null || !errors.containsKey(key)) {
            throw new TechnicalException("El mensaje para la clave '" + key + "' no está configurado.");
        }
        return errors.get(key);
    }

}
