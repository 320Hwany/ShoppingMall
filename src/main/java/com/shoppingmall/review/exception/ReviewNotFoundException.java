package com.shoppingmall.review.exception;

import com.shoppingmall.global.exception.NotFoundException;

public class ReviewNotFoundException extends NotFoundException {

    private static final String MESSAGE = "게시글을 찾을 수 없습니다.";

    public ReviewNotFoundException() {
        super(MESSAGE);
    }
}
