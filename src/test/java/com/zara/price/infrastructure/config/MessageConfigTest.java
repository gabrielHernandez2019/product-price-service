package com.zara.price.infrastructure.config;

import com.zara.price.infrastructure.exception.TechnicalException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageConfigTest {

    @Test
    void testGetErrorMessageWithValidKey() {
        MessageConfig messageConfig = new MessageConfig();
        Map<String, String> errors = new HashMap<>();
        errors.put("key1", "Error message 1");
        messageConfig.setErrors(errors);

        String result = messageConfig.getErrorMessage("key1");

        assertEquals("Error message 1", result);
    }

    @Test
    void testGetErrorMessageWithInvalidKey() {
        MessageConfig messageConfig = new MessageConfig();
        Map<String, String> errors = new HashMap<>();
        errors.put("key1", "Error message 1");
        messageConfig.setErrors(errors);

        TechnicalException exception = assertThrows(TechnicalException.class, () -> {
            messageConfig.getErrorMessage("key2");
        });

        assertEquals("El mensaje para la clave 'key2' no está configurado.", exception.getMessage());
    }

    @Test
    void testGetErrorMessageWithNullErrors() {
        MessageConfig messageConfig = new MessageConfig();

        TechnicalException exception = assertThrows(TechnicalException.class, () -> {
            messageConfig.getErrorMessage("key1");
        });

        assertEquals("El mensaje para la clave 'key1' no está configurado.", exception.getMessage());
    }
}