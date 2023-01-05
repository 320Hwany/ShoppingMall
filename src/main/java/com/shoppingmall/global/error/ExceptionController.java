package com.shoppingmall.global.error;

import com.shoppingmall.item.exception.ItemNotFoundException;
import com.shoppingmall.global.exception.NotFoundException;
import com.shoppingmall.review.exception.ReviewNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> invalidRequest(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            errorResponse.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ResponseBody
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorResponse> postNotFound(NotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getStatusCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(NOT_FOUND).body(errorResponse);
    }

    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> itemNotFound(ItemNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getStatusCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(NOT_FOUND).body(errorResponse);
    }
}
