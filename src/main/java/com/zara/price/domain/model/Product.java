package com.zara.price.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
}