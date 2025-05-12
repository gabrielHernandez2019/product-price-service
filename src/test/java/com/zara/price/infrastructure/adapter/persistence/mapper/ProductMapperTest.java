package com.zara.price.infrastructure.adapter.persistence.mapper;

import com.zara.price.domain.model.Product;
import com.zara.price.infrastructure.adapter.persistence.ProductEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {

    @Test
    void testToDomain() {
        ProductMapper mapper = new ProductMapper();

        ProductEntity entity = new ProductEntity();
        entity.setProductId(1);
        entity.setBrandId(2);
        entity.setStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        entity.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));
        entity.setPrice(BigDecimal.valueOf(99.99));

        Product product = mapper.toDomain(entity);

        assertEquals(entity.getProductId(), product.getId());
        assertEquals(entity.getBrandId(), product.getBrandId());
        assertEquals(entity.getStartDate(), product.getStartDate());
        assertEquals(entity.getEndDate(), product.getEndDate());
        assertEquals(entity.getPrice(), product.getPrice());
    }
}