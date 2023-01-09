package com.shoppingmall.member.domain;

import com.shoppingmall.member.dto.request.MemberUpdate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("회원정보 수정 테스트 - domain")
    void update() {
        // given
        Member member = Member.builder()
                .name("회원 이름")
                .email("yhwjd99@gmail.com")
                .password("1234")
                .age(25)
                .build();

        MemberUpdate memberUpdate = MemberUpdate.builder()
                .name("수정 회원 이름")
                .email("yhwjd99@naver.com")
                .password("4321")
                .age(20)
                .build();

        // when
        member.update(memberUpdate);

        // then
        Assertions.assertThat(member.getName()).isEqualTo("수정 회원 이름");
        Assertions.assertThat(member.getEmail()).isEqualTo("yhwjd99@naver.com");
        Assertions.assertThat(member.getPassword()).isEqualTo("4321");
        Assertions.assertThat(member.getAge()).isEqualTo(20);
    }
}