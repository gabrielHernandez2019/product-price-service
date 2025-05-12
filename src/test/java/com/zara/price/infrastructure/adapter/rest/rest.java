package com.zara.price.infrastructure.adapter.rest;

import com.zara.price.application.service.ProductService;
import com.zara.price.domain.model.Product;
import com.zara.price.infrastructure.adapter.rest.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Test
    void testGetPriceForProduct() {
        ProductService mockService = mock(ProductService.class);
        ProductController controller = new ProductController(mockService);

        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;

        List<Product> mockProducts = Collections.singletonList(new Product());
        when(mockService.findProductsByCriteria(applicationDate, productId, brandId)).thenReturn(mockProducts);

        List<Product> result = controller.getPriceForProduct(applicationDate, productId, brandId);

        assertEquals(mockProducts, result);
        verify(mockService).findProductsByCriteria(applicationDate, productId, brandId);
    }

    @Test
    void testGetPriceForProductWithEmptyResult() {
        ProductService mockService = mock(ProductService.class);
        ProductController controller = new ProductController(mockService);

        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;

        when(mockService.findProductsByCriteria(applicationDate, productId, brandId)).thenReturn(Collections.emptyList());

        List<Product> result = controller.getPriceForProduct(applicationDate, productId, brandId);

        assertEquals(0, result.size());
        verify(mockService).findProductsByCriteria(applicationDate, productId, brandId);
    }

    @Test
    void testHandleMissingServletRequestParameterException() {

        GlobalExceptionHandler controller = new GlobalExceptionHandler();

        MissingServletRequestParameterException exception = new MissingServletRequestParameterException("param", "String");

        ResponseEntity<String> response = controller.handleMissingParams(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Missing parameter: param", response.getBody());
    }
}