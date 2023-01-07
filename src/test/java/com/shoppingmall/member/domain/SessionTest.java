package com.shoppingmall.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseCookie;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("쿠키 생성 테스트 - domain")
    void setCookie() {

        ResponseCookie cookie = Session.setCookie("accessToken");

        assertThat(cookie.getDomain()).isEqualTo("localhost");
        assertThat(cookie.getPath()).isEqualTo("/");
        assertThat(cookie.getMaxAge().getSeconds()).isEqualTo(2592000);
        assertThat(cookie.getDomain()).isEqualTo("localhost");
        assertThat(cookie.getSameSite()).isEqualTo("Strict");
    }
}
