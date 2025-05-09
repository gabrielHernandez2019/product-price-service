package com.zara.price.application.service;

import com.zara.price.domain.exception.BusinessException;
import com.zara.price.domain.model.Product;
import com.zara.price.domain.port.out.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public List<Product> findProductsByCriteria(LocalDateTime applicationDate, Integer productId, Integer brandId) {

        if (Objects.isNull(applicationDate)) {
            throw new BusinessException("La fecha de aplicación no puede ser nula.");
        }

        return repository.findByCriteria(applicationDate, productId, brandId);
    }

}
