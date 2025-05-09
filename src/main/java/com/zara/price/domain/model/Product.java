package com.zara.price.domain.model;

import com.zara.price.domain.exception.BusinessException;
import com.zara.price.infrastructure.exception.TechnicalException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public void validateApplicationDate(LocalDateTime applicationDate) {

    }
}