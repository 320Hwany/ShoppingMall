package com.shoppingmall.auth.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSession {

    private Long id;

    @Builder
    public UserSession(Long id) {
        this.id = id;
    }
}
