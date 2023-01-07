package com.shoppingmall.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
                .sessionList(new ArrayList<>())
                .build();

        Session session = member.addSession();
        // expected
        assertThat(session.getMember()).isEqualTo(member);
        assertThat(member.getSessionList().size()).isEqualTo(1);
        assertThat(member.getSessionList().get(0)).isEqualTo(session);
    }
}