package com.zara.price.application.service;

import com.zara.price.domain.exception.BusinessException;
import com.zara.price.domain.model.Product;
import com.zara.price.domain.port.out.ProductRepository;
import com.zara.price.infrastructure.adapter.kafka.KafkaProducerAdapter;
import com.zara.price.infrastructure.config.MessageConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;
    private MessageConfig messageConfig;
    private KafkaProducerAdapter kafkaProducerAdapter;


    @BeforeEach
    void setUp() {
        kafkaProducerAdapter = mock(KafkaProducerAdapter.class);
        productRepository = mock(ProductRepository.class);
        messageConfig = mock(MessageConfig.class);
        productService = new ProductService(productRepository, messageConfig,kafkaProducerAdapter);
    }

    @Test
    void shouldThrowExceptionWhenApplicationDateIsNull() {
        // Arrange
        String errorMessage = "La fecha de aplicación no puede ser nula.";
        when(messageConfig.getErrorMessage("null-application-date")).thenReturn(errorMessage);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class,
                () -> productService.findProductsByCriteria(null, 1, 1));
        assertEquals(errorMessage, exception.getMessage());
        verify(messageConfig).getErrorMessage("null-application-date");
        verifyNoInteractions(productRepository);
    }

    @Test
    void testRequestAt10AMOn14th() {

        LocalDateTime applicationDate = LocalDateTime.parse("2023-06-14T10:00:00");
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(1, 1, LocalDateTime.parse("2023-06-14T00:00:00"), LocalDateTime.parse("2023-06-14T23:59:59"), BigDecimal.valueOf(35.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId)).thenReturn(expectedProduct);

        Product result = productService.findProductsByCriteria(applicationDate, productId, brandId);

        assertEquals(expectedProduct, result);
    }

    @Test
    void testRequestAt4PMOn14th() {
        LocalDateTime applicationDate = LocalDateTime.parse("2023-06-14T16:00:00");
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(2, 1, LocalDateTime.parse("2023-06-14T15:00:00"), LocalDateTime.parse("2023-06-14T18:00:00"), BigDecimal.valueOf(20.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId)).thenReturn(expectedProduct);

        Product result = productService.findProductsByCriteria(applicationDate, productId, brandId);

        assertEquals(expectedProduct, result);
    }

    @Test
    void testRequestAt9PMOn14th() {
        LocalDateTime applicationDate = LocalDateTime.parse("2023-06-14T21:00:00");
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(3, 1, LocalDateTime.parse("2023-06-14T20:00:00"), LocalDateTime.parse("2023-06-14T23:59:59"), BigDecimal.valueOf(30.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId)).thenReturn(expectedProduct);

        Product result = productService.findProductsByCriteria(applicationDate, productId, brandId);


        assertEquals(expectedProduct, result);
    }

    @Test
    void testRequestAt10AMOn15th() {
        LocalDateTime applicationDate = LocalDateTime.parse("2023-06-15T10:00:00");
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(4, 1, LocalDateTime.parse("2023-06-15T00:00:00"), LocalDateTime.parse("2023-06-15T23:59:59"), BigDecimal.valueOf(30.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId)).thenReturn(expectedProduct);

        Product result = productService.findProductsByCriteria(applicationDate, productId, brandId);


        assertEquals(expectedProduct, result);
    }

    @Test
    void testRequestAt9PMOn16th() {
        LocalDateTime applicationDate = LocalDateTime.parse("2023-06-16T21:00:00");
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(5, 1, LocalDateTime.parse("2023-06-16T20:00:00"), LocalDateTime.parse("2023-06-16T23:59:59"), BigDecimal.valueOf(30.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId)).thenReturn(expectedProduct);

        Product result = productService.findProductsByCriteria(applicationDate, productId, brandId);

        assertEquals(expectedProduct, result);
    }
}
