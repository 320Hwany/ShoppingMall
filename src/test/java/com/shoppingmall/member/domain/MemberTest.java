package com.shoppingmall.member.domain;

import com.shoppingmall.utils.AccessTokenHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("세션 생성 테스트 - domain")
    void addSession() {
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
        // expected
        assertThat(session.getMember()).isEqualTo(member);
    }

    private class TestAccessTokenHolder implements AccessTokenHolder {

        private static final String MESSAGE = "testToken";

        @Override
        public String getAccessToken() {
            return MESSAGE;
        }
    }
}