package com.shoppingmall.exception.e404;

import lombok.Getter;

@Getter
public abstract class NotFoundException extends RuntimeException{

    private final String statusCode = "404";

    public NotFoundException(String message) {
        super(message);
    }
}
