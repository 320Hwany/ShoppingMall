package com.shoppingmall.member.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {

    private final String statusCode = "401";

    private static final String MESSAGE = "로그인 후 이용해주세요";

    public UnauthorizedException() {
        super(MESSAGE);
    }
}
