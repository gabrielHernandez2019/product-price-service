package com.zara.price.infrastructure.adapter.persistence;

import com.zara.price.domain.model.Product;
import com.zara.price.domain.port.out.ProductRepository;
import com.zara.price.infrastructure.adapter.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryImpl(ProductJpaRepository jpaRepository, ProductMapper productMapper) {
        this.jpaRepository = jpaRepository;
        this.productMapper = productMapper;
    }

    /**
     * Retrieves a list of products based on the provided application date, product ID, and brand ID.
     *
     * @param applicationDate the application date in 'yyyy-MM-dd' format
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @return a list of products matching the criteria
     */

    @Override
    public List<Product> findByCriteria(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return jpaRepository.findByCriteria(applicationDate, productId, brandId).stream().map(productMapper::toDomain).toList();
    }


}
