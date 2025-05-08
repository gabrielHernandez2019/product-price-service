package com.zara.price.application.service;

import com.zara.price.domain.model.Product;
import com.zara.price.domain.port.out.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void testRequestAt10AMOn14th() {
        String applicationDate = "2023-06-14T10:00:00";
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(1, 1,
                LocalDateTime.parse("2023-06-14T00:00:00"),
                LocalDateTime.parse("2023-06-14T23:59:59"),
                BigDecimal.valueOf(35.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId))
                .thenReturn(List.of(expectedProduct));

        List<Product> result = productService.findProductsByCriteria(applicationDate, productId, brandId);

        assertEquals(1, result.size());
        assertEquals(expectedProduct, result.get(0));
    }

    @Test
    void testRequestAt4PMOn14th() {
        String applicationDate = "2023-06-14T16:00:00";
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(2, 1,
                LocalDateTime.parse("2023-06-14T15:00:00"),
                        LocalDateTime.parse("2023-06-14T18:00:00"),
                BigDecimal.valueOf(20.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId))
                .thenReturn(List.of(expectedProduct));

        List<Product> result = productService.findProductsByCriteria(applicationDate, productId, brandId);

        assertEquals(1, result.size());
        assertEquals(expectedProduct, result.get(0));
    }

    @Test
    void testRequestAt9PMOn14th() {
        String applicationDate = "2023-06-14T21:00:00";
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(3, 1,
                LocalDateTime.parse("2023-06-14T20:00:00"),
                LocalDateTime.parse("2023-06-14T23:59:59"),
                BigDecimal.valueOf(30.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId))
                .thenReturn(List.of(expectedProduct));

        List<Product> result = productService.findProductsByCriteria(applicationDate, productId, brandId);

        assertEquals(1, result.size());
        assertEquals(expectedProduct, result.get(0));
    }

    @Test
    void testRequestAt10AMOn15th() {
        String applicationDate = "2023-06-15T10:00:00";
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(4, 1,
                LocalDateTime.parse("2023-06-15T00:00:00"),
                        LocalDateTime.parse("2023-06-15T23:59:59"),
                BigDecimal.valueOf(30.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId))
                .thenReturn(List.of(expectedProduct));

        List<Product> result = productService.findProductsByCriteria(applicationDate, productId, brandId);

        assertEquals(1, result.size());
        assertEquals(expectedProduct, result.get(0));
    }

    @Test
    void testRequestAt9PMOn16th() {
        String applicationDate = "2023-06-16T21:00:00";
        Integer productId = 35455;
        Integer brandId = 1;

        Product expectedProduct = new Product(5, 1,
                LocalDateTime.parse("2023-06-16T20:00:00"),
                        LocalDateTime.parse("2023-06-16T23:59:59"),
                                BigDecimal.valueOf(30.50));
        when(productRepository.findByCriteria(applicationDate, productId, brandId))
                .thenReturn(List.of(expectedProduct));

        List<Product> result = productService.findProductsByCriteria(applicationDate, productId, brandId);

        assertEquals(1, result.size());
        assertEquals(expectedProduct, result.get(0));
    }
}
