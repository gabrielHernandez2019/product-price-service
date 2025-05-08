package com.zara.price.infrastructure.adapter.rest;

import com.zara.price.application.service.ProductService;
import com.zara.price.domain.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
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
    public List<Product> getAll(
            @RequestParam("applicationDate") String applicationDate,
            @RequestParam("productId") Integer productId,
            @RequestParam("brandId") Integer brandId) {
        return service.findProductsByCriteria(applicationDate, productId, brandId);
    }
}
