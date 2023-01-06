package com.shoppingmall.member.domain;

import com.shoppingmall.auth.domain.LoginToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("로그인 토큰 생성 테스트 - domain")
    void addLoginToken() {
        // given
        Member member = Member.builder()
                .name("회원이름")
                .email("yhwjd99@gmail.com")
                .password("1234")
                .age(25)
                .loginTokenList(new ArrayList<>())
                .build();

        // expected
        assertThat(member.addLoginToken()).isEqualTo(member.getLoginTokenList().get(0));
    }
}