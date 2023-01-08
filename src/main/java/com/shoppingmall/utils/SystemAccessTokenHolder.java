package com.shoppingmall.utils;

import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
public class SystemAccessTokenHolder implements AccessTokenHolder{
    @Override
    public String getAccessToken() {
        return randomUUID().toString();
    }
}
