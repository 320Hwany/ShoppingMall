package com.shoppingmall.controller;

import com.shoppingmall.response.ValidErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidErrorResponse> invalidRequest(MethodArgumentNotValidException e) {
        ValidErrorResponse validErrorResponse = new ValidErrorResponse();

        for (FieldError fieldError : e.getFieldErrors()) {
            validErrorResponse.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(validErrorResponse);
    }
}
