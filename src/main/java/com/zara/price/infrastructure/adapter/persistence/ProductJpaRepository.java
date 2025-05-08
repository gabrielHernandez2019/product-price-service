package com.zara.price.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE " +
            "(:applicationDate BETWEEN p.startDate AND p.endDate) AND " +
            "(:productId IS NULL OR p.id = :productId) AND " +
            "(:brandId IS NULL OR p.brandId = :brandId)")
    List<ProductEntity> findByCriteria(@Param("applicationDate") LocalDateTime applicationDate,
                                       @Param("productId") Integer productId,
                                       @Param("brandId") Integer brandId);
}
