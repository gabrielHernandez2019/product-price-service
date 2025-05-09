package com.zara.price.infrastructure.adapter.rest.exception;

import com.zara.price.domain.exception.BusinessException;
import com.zara.price.infrastructure.exception.TechnicalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<String> handleTechnicalException(TechnicalException ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
