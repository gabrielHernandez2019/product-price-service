package com.zara.price.infrastructure.adapter.rest;

import com.zara.price.application.service.ProductService;
import com.zara.price.domain.model.Product;
import com.zara.price.infrastructure.adapter.rest.dto.PriceDto;
import com.zara.price.infrastructure.adapter.rest.mapper.ProductDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/prices")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {

        this.service = service;
    }

    /**
     * Endpoint to get all products based on application date, product ID, and brand ID.
     *
     * @param applicationDate the application date in 'yyyy-MM-dd' format
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @return a list of products matching the criteria
     */
    @GetMapping
    public PriceDto.ProductPriceDto getPriceForProduct(
            @RequestParam(value = "applicationDate", required = true) LocalDateTime applicationDate,
            @RequestParam(value = "productId", required = true) Integer productId,
            @RequestParam(value = "brandId", required = true) Integer brandId) {
        
        Product product =  service.findProductsByCriteria(applicationDate, productId, brandId);

        return ProductDtoMapper.toDto(product);

    }
}
