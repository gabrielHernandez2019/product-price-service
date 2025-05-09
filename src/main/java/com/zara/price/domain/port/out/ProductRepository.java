package com.zara.price.domain.port.out;

import com.zara.price.domain.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {
    List<Product> findByCriteria(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
