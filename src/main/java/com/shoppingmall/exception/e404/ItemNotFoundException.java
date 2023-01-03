package com.shoppingmall.exception.e404;

public class ItemNotFoundException extends NotFoundException {

    private static final String MESSAGE = "상품을 찾을 수 없습니다.";

    public ItemNotFoundException() {
        super(MESSAGE);
    }
}
