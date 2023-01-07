package com.shoppingmall.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("세션 생성 테스트 - domain")
    void addLoginToken() {
        // given
        Member member = Member.builder()
                .name("회원이름")
                .email("yhwjd99@gmail.com")
                .password("1234")
                .age(25)
                .sessionList(new ArrayList<>())
                .build();

        // expected
        assertThat(member.addSession()).isEqualTo(member.getSessionList().get(0));
    }
}