package com.zara.price.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {
    private final String errorCode;
    private final String message;


    public BusinessException(@NonNull String message, @NonNull String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

}