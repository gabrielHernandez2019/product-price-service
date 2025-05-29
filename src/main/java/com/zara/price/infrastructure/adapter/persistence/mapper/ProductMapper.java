package com.zara.price.infrastructure.adapter.persistence.mapper;

import com.zara.price.domain.model.Product;
import com.zara.price.infrastructure.adapter.persistence.ProductEntity;
import com.zara.price.infrastructure.adapter.rest.dto.PriceDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toDomain(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getProductId());
        product.setBrandId(entity.getBrandId());
        product.setStartDate(entity.getStartDate());
        product.setEndDate(entity.getEndDate());
        product.setPrice(entity.getPrice());
        return product;
    }


}
