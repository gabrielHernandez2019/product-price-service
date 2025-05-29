package com.zara.price.infrastucture.adapter.persistence;

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