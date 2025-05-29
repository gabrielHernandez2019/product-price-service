package com.zara.price.infrastructure.adapter.persistence;

import com.zara.price.infrastructure.adapter.persistence.ProductEntity;
import com.zara.price.infrastructure.adapter.persistence.ProductJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
class ProductJpaRepositoryTest {

    @Autowired
    private ProductJpaRepository productJpaRepository;


    @Test
    void inspectDatabase() {
        List<ProductEntity> allProducts = productJpaRepository.findAll();
        allProducts.forEach(product -> assertNotNull(product.getProductId(), "El ID del producto no debe ser nulo"));
    }


    @Test
    void testRequestAt10AMOn14th() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Optional<ProductEntity> result = productJpaRepository
                .findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        applicationDate, applicationDate, productId, brandId);

        assertTrue(result.isPresent(), "Debe encontrarse un producto para la petición de las 10:00 del 14/06");
        assertEquals(productId, result.get().getProductId());
        assertEquals(brandId, result.get().getBrandId());
    }

    @Test
    void testRequestAt4PMOn14th() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Optional<ProductEntity> result = productJpaRepository
                .findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        applicationDate, applicationDate, productId, brandId);

        assertTrue(result.isPresent(), "Debe encontrarse un producto para la petición de las 16:00 del 14/06");
        assertEquals(productId, result.get().getProductId());
        assertEquals(brandId, result.get().getBrandId());
    }

    @Test
    void testRequestAt9PMOn14th() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Optional<ProductEntity> result = productJpaRepository
                .findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        applicationDate, applicationDate, productId, brandId);

        assertTrue(result.isPresent(), "Debe encontrarse un producto para la petición de las 21:00 del 14/06");
        assertEquals(productId, result.get().getProductId());
        assertEquals(brandId, result.get().getBrandId());
    }

    @Test
    void testRequestAt10AMOn15th() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Optional<ProductEntity> result = productJpaRepository
                .findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        applicationDate, applicationDate, productId, brandId);

        assertTrue(result.isPresent(), "Debe encontrarse un producto para la petición de las 10:00 del 15/06");
        assertEquals(productId, result.get().getProductId());
        assertEquals(brandId, result.get().getBrandId());
    }

    @Test
    void testRequestAt9PMOn16th() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Optional<ProductEntity> result = productJpaRepository
                .findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        applicationDate, applicationDate, productId, brandId);

        assertTrue(result.isPresent(), "Debe encontrarse un producto para la petición de las 21:00 del 16/06");
        assertEquals(productId, result.get().getProductId());
        assertEquals(brandId, result.get().getBrandId());
    }

    @Test
    void testFindByCriteriaWithAllParameters() {
        String applicationDate = "2020-06-14T10:00:00";
        Integer productId = 35455;
        Integer brandId = 1;
        inspectDatabase();
        Optional<ProductEntity> result = productJpaRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(LocalDateTime.parse(applicationDate), LocalDateTime.parse(applicationDate),productId, brandId);

        assertEquals(false, result.isEmpty());
        assertEquals(35455, result.get().getProductId());
    }

    @Test
    void testFindByCriteriaWithNullProductId() {
        String applicationDate = "2023-06-14T10:00:00";
        Integer productId = null;
        Integer brandId = 1;

        Optional<ProductEntity> result = productJpaRepository
                .findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        LocalDateTime.parse(applicationDate),
                        LocalDateTime.parse(applicationDate),
                        productId,
                        brandId
                );

        assertTrue(result.isEmpty(), "No debe encontrarse producto si el productId es nulo");
    }

    @Test
    void testFindByCriteriaWithNullBrandId() {
        String applicationDate = "2023-06-14T10:00:00";
        Integer productId = 35455;
        Integer brandId = null;

        Optional<ProductEntity> result = productJpaRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(LocalDateTime.parse(applicationDate), LocalDateTime.parse(applicationDate),productId, brandId);

        assertEquals(true, result.isEmpty()); // Ajusta según los datos de prueba
    }

    @Test
    void testFindByCriteriaWithNullParameters() {
        String applicationDate = "2023-06-14T10:00:00";
        Integer productId = null;
        Integer brandId = null;

        Optional<ProductEntity> result = productJpaRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(LocalDateTime.parse(applicationDate),LocalDateTime.parse(applicationDate), productId, brandId);

        assertEquals(true, result.isEmpty()); // Ajusta según los datos de prueba
    }
}