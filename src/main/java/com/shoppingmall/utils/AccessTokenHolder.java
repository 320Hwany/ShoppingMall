package com.shoppingmall.utils;

import org.springframework.stereotype.Component;

import static java.util.UUID.*;

@Component
public interface AccessTokenHolder {

    public String getAccessToken();
}
