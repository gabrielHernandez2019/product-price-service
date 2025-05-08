package com.zara.price.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCriteria(String applicationDate, Integer productId, Integer brandId);
}
