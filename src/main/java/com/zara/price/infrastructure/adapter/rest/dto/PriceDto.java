package com.zara.price.infrastructure.adapter.rest.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PriceDto {

        private Integer productId;
        private Integer brandId;
        private LocalDateTime applicationDate;
        private LocalDateTime endDate;
        private BigDecimal price;

        public PriceDto(Integer productId, Integer brandId, LocalDateTime applicationDate, LocalDateTime endDate, BigDecimal price) {
            this.productId = productId;
            this.brandId = brandId;
            this.applicationDate = applicationDate;
            this.endDate = endDate;
            this.price = price;
        }
}


