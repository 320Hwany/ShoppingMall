package com.shoppingmall.exception.e404;

public class ReviewNotFoundException extends NotFoundException {

    private static final String MESSAGE = "게시글을 찾을 수 없습니다.";

    public ReviewNotFoundException() {
        super(MESSAGE);
    }
}
