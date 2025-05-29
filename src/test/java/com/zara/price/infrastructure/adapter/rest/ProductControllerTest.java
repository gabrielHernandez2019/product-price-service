package com.zara.price.infrastructure.adapter.rest;

import com.zara.price.application.service.ProductService;
import com.zara.price.domain.model.Product;
import com.zara.price.infrastructure.adapter.rest.exception.GlobalExceptionHandler;
import com.zara.price.infrastructure.adapter.rest.exception.dto.ErrorDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ProductControllerTest {


    @Test
    void testRequestAt4PMOn14th() {
        ProductService mockService = mock(ProductService.class);
        ProductController controller = new ProductController(mockService);

        LocalDateTime applicationDate = LocalDateTime.of(2023, 6, 14, 16, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Product mockProducts = new Product( 1, brandId, applicationDate, LocalDateTime.of(2023, 6, 14, 23, 59), BigDecimal.valueOf(38.95));
        when(mockService.findProductsByCriteria(applicationDate, productId, brandId)).thenReturn(mockProducts);

        Product result = controller.getPriceForProduct(applicationDate, productId, brandId);

        assertEquals(mockProducts, result);
        verify(mockService).findProductsByCriteria(applicationDate, productId, brandId);
    }

    @Test
    void testRequestAt9PMOn14th() {
        ProductService mockService = mock(ProductService.class);
        ProductController controller = new ProductController(mockService);

        LocalDateTime applicationDate = LocalDateTime.of(2023, 6, 14, 21, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Product mockProducts = new Product();
        when(mockService.findProductsByCriteria(applicationDate, productId, brandId)).thenReturn(mockProducts);

        Product result = controller.getPriceForProduct(applicationDate, productId, brandId);

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

        when(mockService.findProductsByCriteria(applicationDate, productId, brandId)).thenReturn(null);

        Product result = controller.getPriceForProduct(applicationDate, productId, brandId);

        assertNull(result);
        verify(mockService).findProductsByCriteria(applicationDate, productId, brandId);
    }

    @Test
    void testHandleMissingServletRequestParameterException() {

        GlobalExceptionHandler controller = new GlobalExceptionHandler();

        MissingServletRequestParameterException exception = new MissingServletRequestParameterException("param", "String");

        ResponseEntity<ErrorDto> response = controller.handleMissingParams(exception);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}