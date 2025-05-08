package com.zara.price.infrastructure.adapter.persistence;

import com.zara.price.domain.model.Product;
import com.zara.price.domain.port.out.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;

    public ProductRepositoryImpl(ProductJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
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
    public List<Product> findByCriteria(String applicationDate, Integer productId, Integer brandId) {
        return jpaRepository.findByCriteria(applicationDate, productId, brandId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Converts a ProductEntity to a Product domain model.
     *
     * @param entity the ProductEntity to convert
     * @return the converted Product domain model
     */
    private Product toDomain(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getProductId());
        product.setBrandId(entity.getBrandId());
        product.setStartDate(entity.getStartDate());
        product.setEndDate(entity.getEndDate());
        product.setPrice(entity.getPrice());
        return product;
    }
}
