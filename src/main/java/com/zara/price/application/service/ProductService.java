package com.zara.price.application.service;

import com.zara.price.domain.model.Product;
import com.zara.price.domain.port.out.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a list of products based on the provided application date, product ID, and brand ID.
     *
     * @param applicationDate the application date in 'yyyy-MM-dd' format
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @return a list of products matching the criteria
     */


    public List<Product> findProductsByCriteria(String applicationDate, Integer productId, Integer brandId) {
        return repository.findByCriteria(applicationDate, productId, brandId);
    }

}
