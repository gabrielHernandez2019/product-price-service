package com.zara.price.application.service;

import com.zara.price.domain.exception.BusinessException;
import com.zara.price.domain.model.Product;
import com.zara.price.domain.port.out.ProductRepository;
import com.zara.price.infrastructure.adapter.kafka.KafkaProducerAdapter;
import com.zara.price.infrastructure.config.MessageConfig;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final MessageConfig messageConfig;
    private final KafkaProducerAdapter kafkaProducerAdapter;


    public ProductService(ProductRepository repository, MessageConfig messageConfig, KafkaProducerAdapter kafkaProducerAdapter) {
        this.repository = repository;
        this.messageConfig = messageConfig;
        this.kafkaProducerAdapter = kafkaProducerAdapter;
    }


    public List<Product> findProductsByCriteria(LocalDateTime applicationDate, Integer productId, Integer brandId) {

        if (Objects.isNull(applicationDate)) {
            throw new BusinessException(messageConfig.getErrorMessage("null-application-date"), HttpStatus.BAD_REQUEST.toString());
        }
        kafkaProducerAdapter.sendMessage("productId: " + productId + ", brandId: " + brandId + ", applicationDate: " + applicationDate);
        return repository.findByCriteria(applicationDate, productId, brandId);
    }

}
