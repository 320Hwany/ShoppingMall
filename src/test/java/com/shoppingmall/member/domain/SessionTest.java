package com.shoppingmall.member.domain;

import com.shoppingmall.utils.AccessTokenHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseCookie;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("쿠키 생성 테스트 - domain")
    void setCookie() {
        // given
        Member member = Member.builder()
                .name("회원이름")
                .email("yhwjd99@gmail.com")
                .password("1234")
                .age(25)
                .build();

        Session session = Session.builder()
                .member(member)
                .accessTokenHolder(new TestAccessTokenHolder())
                .build();

        // when
        ResponseCookie cookie = session.setCookie();

        // then
        assertThat(cookie.getName()).isEqualTo("SESSION");
        assertThat(cookie.getValue()).isEqualTo("testToken");
        assertThat(cookie.getDomain()).isEqualTo("localhost");
        assertThat(cookie.getPath()).isEqualTo("/");
        assertThat(cookie.getMaxAge().getSeconds()).isEqualTo(2592000);
        assertThat(cookie.getDomain()).isEqualTo("localhost");
        assertThat(cookie.getSameSite()).isEqualTo("Strict");
    }

    private class TestAccessTokenHolder implements AccessTokenHolder {

        private static final String MESSAGE = "testToken";

        @Override
        public String getAccessToken() {
            return MESSAGE;
        }
    }
}
