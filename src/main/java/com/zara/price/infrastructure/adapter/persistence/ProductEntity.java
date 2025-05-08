package com.zara.price.infrastructure.adapter.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "PRICES")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer brandId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Integer priceList;
    private Integer productId;
    private Integer priority;

    private BigDecimal price;
    private String curr;


}
