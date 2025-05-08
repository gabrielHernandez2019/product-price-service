package com.zara.price.domain.port.out;

import com.zara.price.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll();
}
