package com.shoppingmall.response;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidErrorResponse {

    private final String code = "400";
    private final String message = "잘못된 요청입니다.";

    private Map<String, String> validation = new HashMap<>();

    public void addValidation(String fieldName, String errorMessage) {
        validation.put(fieldName, errorMessage);
    }
}
