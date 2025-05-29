package com.zara.price.infrastructure.adapter.rest.mapper;

import com.zara.price.domain.model.Product;
import com.zara.price.infrastructure.adapter.rest.dto.PriceDto;

import java.util.Objects;

public class ProductDtoMapper {

    public static PriceDto toDto(Product product) {
        return Objects.isNull(product) ? null :
        new PriceDto(
                product.getId(),
                product.getBrandId(),
                product.getStartDate(),
                product.getEndDate(),
                product.getPrice()
        );
    }

}
