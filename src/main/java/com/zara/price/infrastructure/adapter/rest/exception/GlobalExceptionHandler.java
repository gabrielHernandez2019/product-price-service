package com.zara.price.infrastructure.adapter.rest.exception;

import com.zara.price.domain.exception.BusinessException;
import com.zara.price.infrastructure.adapter.rest.exception.dto.ErrorDto;
import com.zara.price.infrastructure.exception.TechnicalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ErrorDto> handleTechnicalException(TechnicalException ex) {
        ErrorDto error = new ErrorDto(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDto> handleBusinessException(BusinessException ex) {
        ErrorDto error = new ErrorDto(String.valueOf(HttpStatus.BAD_REQUEST.value()),ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDto> handleMissingParams(MissingServletRequestParameterException ex) {
        ErrorDto error = new ErrorDto(String.valueOf(HttpStatus.BAD_REQUEST.value()),ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
