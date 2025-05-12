package com.zara.price.domain.exception;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BusinessExceptionTest {

    @Test
    void testBusinessExceptionMessage() {
        String expectedMessage = "Error de negocio";

        BusinessException exception = new BusinessException(expectedMessage);
        assertNotNull(exception, "La excepción no debe ser nula");
        assertEquals(expectedMessage, exception.getMessage(), "El mensaje de la excepción no coincide");
    }
}
