package com.zara.price.infrastructure.adapter.rest;

import com.zara.price.application.service.ProductService;
import com.zara.price.domain.exception.BusinessException;
import com.zara.price.domain.model.Product;
import com.zara.price.infrastructure.adapter.rest.dto.PriceDto;
import com.zara.price.infrastructure.adapter.rest.exception.GlobalExceptionHandler;
import com.zara.price.infrastructure.adapter.rest.exception.dto.ErrorDto;
import com.zara.price.infrastructure.exception.TechnicalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductController - Consulta de precios por fecha, producto y marca")
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Nested
    @DisplayName("Casos de prueba - Producto 35455, Marca ZARA")
    class RequiredTestScenarios {

        @Test
        @DisplayName("Consulta a las 10:00 del 14 de junio - Aplicar tarifa base de 35.50 EUR")
        void shouldApplyBasePriceAt10AM_June14th_ForProduct35455_BrandZara() {

            LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
            Integer productId = 35455;
            Integer brandId = 1;

            Product mockProduct = new Product(
                    productId,
                    brandId,
                    LocalDateTime.of(2020, 6, 14, 0, 0),
                    LocalDateTime.of(2020, 12, 31, 23, 59),
                    BigDecimal.valueOf(35.50)
            );

            when(productService.findProductsByCriteria(applicationDate, productId, brandId))
                    .thenReturn(mockProduct);


            PriceDto result = productController.getPriceForProduct(applicationDate, productId, brandId);


            assertThat(result).isNotNull();
            assertThat(result.getProductId()).isEqualTo(productId);
            assertThat(result.getBrandId()).isEqualTo(brandId);
            assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(35.50));
            assertThat(result.getApplicationDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 0, 0)); // startDate del Product
            assertThat(result.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59));

            verify(productService).findProductsByCriteria(applicationDate, productId, brandId);
        }

        @Test
        @DisplayName("Consulta a las 16:00 del 14 de junio - Aplicar precio promocional de 25.45 EUR")
        void shouldApplyPromotionalPriceAt4PM_June14th_ForProduct35455_BrandZara() {

            LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
            Integer productId = 35455;
            Integer brandId = 1;

            Product mockProduct = new Product(
                    productId,
                    brandId,
                    LocalDateTime.of(2020, 6, 14, 15, 0),
                    LocalDateTime.of(2020, 6, 14, 18, 30),
                    BigDecimal.valueOf(25.45)
            );

            when(productService.findProductsByCriteria(applicationDate, productId, brandId))
                    .thenReturn(mockProduct);


            PriceDto result = productController.getPriceForProduct(applicationDate, productId, brandId);


            assertThat(result).isNotNull();
            assertThat(result.getProductId()).isEqualTo(productId);
            assertThat(result.getBrandId()).isEqualTo(brandId);
            assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(25.45));
            assertThat(result.getApplicationDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 15, 0)); // startDate del Product
            assertThat(result.getEndDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 18, 30));

            verify(productService).findProductsByCriteria(applicationDate, productId, brandId);
        }

        @Test
        @DisplayName("Consulta a las 21:00 del 14 de junio - Volver a tarifa base de 35.50 EUR")
        void shouldReturnToBasePriceAt9PM_June14th_ForProduct35455_BrandZara() {

            LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);
            Integer productId = 35455;
            Integer brandId = 1;

            Product mockProduct = new Product(
                    productId,
                    brandId,
                    LocalDateTime.of(2020, 6, 14, 0, 0),
                    LocalDateTime.of(2020, 12, 31, 23, 59),
                    BigDecimal.valueOf(35.50)
            );

            when(productService.findProductsByCriteria(applicationDate, productId, brandId))
                    .thenReturn(mockProduct);


            PriceDto result = productController.getPriceForProduct(applicationDate, productId, brandId);


            assertThat(result).isNotNull();
            assertThat(result.getProductId()).isEqualTo(productId);
            assertThat(result.getBrandId()).isEqualTo(brandId);
            assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(35.50));
            assertThat(result.getApplicationDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 0, 0)); // startDate del Product

            verify(productService).findProductsByCriteria(applicationDate, productId, brandId);
        }

        @Test
        @DisplayName("Consulta a las 10:00 del 15 de junio - Aplicar precio matutino de 30.50 EUR")
        void shouldApplyMorningPriceAt10AM_June15th_ForProduct35455_BrandZara() {

            LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
            Integer productId = 35455;
            Integer brandId = 1;

            Product mockProduct = new Product(
                    productId,
                    brandId,
                    LocalDateTime.of(2020, 6, 15, 0, 0),
                    LocalDateTime.of(2020, 6, 15, 11, 0),
                    BigDecimal.valueOf(30.50)
            );

            when(productService.findProductsByCriteria(applicationDate, productId, brandId))
                    .thenReturn(mockProduct);


            PriceDto result = productController.getPriceForProduct(applicationDate, productId, brandId);


            assertThat(result).isNotNull();
            assertThat(result.getProductId()).isEqualTo(productId);
            assertThat(result.getBrandId()).isEqualTo(brandId);
            assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(30.50));
            assertThat(result.getApplicationDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 0, 0)); // startDate del Product
            assertThat(result.getEndDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 11, 0));

            verify(productService).findProductsByCriteria(applicationDate, productId, brandId);
        }

        @Test
        @DisplayName("Consulta a las 21:00 del 15 de junio - Aplicar precio vespertino de 38.95 EUR")
        void shouldApplyEveningPriceAt9PM_June15th_ForProduct35455_BrandZara() {

            LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 21, 0);
            Integer productId = 35455;
            Integer brandId = 1;

            Product mockProduct = new Product(
                    productId,
                    brandId,
                    LocalDateTime.of(2020, 6, 15, 16, 0),
                    LocalDateTime.of(2020, 12, 31, 23, 59),
                    BigDecimal.valueOf(38.95)
            );

            when(productService.findProductsByCriteria(applicationDate, productId, brandId))
                    .thenReturn(mockProduct);


            PriceDto result = productController.getPriceForProduct(applicationDate, productId, brandId);


            assertThat(result).isNotNull();
            assertThat(result.getProductId()).isEqualTo(productId);
            assertThat(result.getBrandId()).isEqualTo(brandId);
            assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(38.95));
            assertThat(result.getApplicationDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 16, 0)); // startDate del Product
            assertThat(result.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59));

            verify(productService).findProductsByCriteria(applicationDate, productId, brandId);
        }
    }

    @Nested
    @DisplayName("Casos adicionales para validar límites y transiciones")
    class AdditionalValidationScenarios {

        @Test
        @DisplayName("Consulta exactamente al inicio de promoción (15:00 del 14 de junio)")
        void shouldApplyPromotionalPriceAtExactStartTime_June14th_3PM() {

            LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 15, 0);
            Integer productId = 35455;
            Integer brandId = 1;

            Product mockProduct = new Product(
                    productId,
                    brandId,
                    LocalDateTime.of(2020, 6, 14, 15, 0),
                    LocalDateTime.of(2020, 6, 14, 18, 30),
                    BigDecimal.valueOf(25.45)
            );

            when(productService.findProductsByCriteria(applicationDate, productId, brandId))
                    .thenReturn(mockProduct);


            PriceDto result = productController.getPriceForProduct(applicationDate, productId, brandId);


            assertThat(result).isNotNull();
            assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(25.45));
            assertThat(result.getApplicationDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 15, 0)); // startDate del Product
            assertThat(result.getProductId()).isEqualTo(productId);

            verify(productService).findProductsByCriteria(applicationDate, productId, brandId);
        }

        @Test
        @DisplayName("Consulta al mediodía del 15 de junio después de precio matutino")
        void shouldApplyCorrectPriceAtNoon_June15th_AfterMorningPeriod() {

            LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 12, 0);
            Integer productId = 35455;
            Integer brandId = 1;

            Product mockProduct = new Product(
                    productId,
                    brandId,
                    LocalDateTime.of(2020, 6, 14, 0, 0),
                    LocalDateTime.of(2020, 12, 31, 23, 59),
                    BigDecimal.valueOf(35.50)
            );

            when(productService.findProductsByCriteria(applicationDate, productId, brandId))
                    .thenReturn(mockProduct);


            PriceDto result = productController.getPriceForProduct(applicationDate, productId, brandId);


            assertThat(result).isNotNull();
            assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(35.50));
            assertThat(result.getApplicationDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 0, 0)); // startDate del Product
            assertThat(result.getProductId()).isEqualTo(productId);

            verify(productService).findProductsByCriteria(applicationDate, productId, brandId);
        }
    }

    @Nested
    @DisplayName("Manejo de casos excepcionales")
    class ExceptionHandlingScenarios {

        @Test
        @DisplayName("Debe retornar null cuando no existe producto para los criterios dados")
        void shouldReturnNullWhenNoProductFoundForGivenCriteria() {

            LocalDateTime applicationDate = LocalDateTime.of(2020, 7, 1, 10, 0);
            Integer productId = 99999;
            Integer brandId = 2;

            when(productService.findProductsByCriteria(applicationDate, productId, brandId))
                    .thenReturn(null);


            PriceDto result = productController.getPriceForProduct(applicationDate, productId, brandId);


            assertNull(result);
            verify(productService).findProductsByCriteria(applicationDate, productId, brandId);
        }
    }

    @Nested
    @DisplayName("GlobalExceptionHandler - Manejo de excepciones")
    class GlobalExceptionHandlerTest {

        private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

        @Test
        @DisplayName("Debe manejar BusinessException retornando BAD_REQUEST con código y mensaje")
        void shouldHandleBusinessExceptionWithBadRequestStatus() {

            String errorMessage = "Invalid product criteria provided";
            BusinessException businessException = new BusinessException(errorMessage);


            ResponseEntity<ErrorDto> response = exceptionHandler.handleBusinessException(businessException);


            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getErrorCode()).isEqualTo("400");
            assertThat(response.getBody().getMessage()).isEqualTo(errorMessage);
        }

        @Test
        @DisplayName("Debe manejar TechnicalException retornando INTERNAL_SERVER_ERROR con código y mensaje")
        void shouldHandleTechnicalExceptionWithInternalServerErrorStatus() {

            String errorMessage = "Database connection failed";
            TechnicalException technicalException = new TechnicalException(errorMessage);


            ResponseEntity<ErrorDto> response = exceptionHandler.handleTechnicalException(technicalException);


            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getErrorCode()).isEqualTo("500");
            assertThat(response.getBody().getMessage()).isEqualTo(errorMessage);
        }

        @Test
        @DisplayName("Debe manejar parámetros faltantes con BAD_REQUEST y mensaje descriptivo")
        void shouldHandleMissingParametersWithProperErrorResponse() {

            String parameterName = "applicationDate";
            String parameterType = "LocalDateTime";
            MissingServletRequestParameterException exception =
                    new MissingServletRequestParameterException(parameterName, parameterType);


            ResponseEntity<ErrorDto> response = exceptionHandler.handleMissingParams(exception);


            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getErrorCode()).isEqualTo("400");
            assertThat(response.getBody().getMessage()).contains(parameterName);
        }

        @Test
        @DisplayName("Debe manejar parámetro productId faltante correctamente")
        void shouldHandleMissingProductIdParameterCorrectly() {

            MissingServletRequestParameterException exception =
                    new MissingServletRequestParameterException("productId", "Integer");


            ResponseEntity<ErrorDto> response = exceptionHandler.handleMissingParams(exception);


            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getErrorCode()).isEqualTo("400");
            assertThat(response.getBody().getMessage()).isNotNull();
        }
    }
}