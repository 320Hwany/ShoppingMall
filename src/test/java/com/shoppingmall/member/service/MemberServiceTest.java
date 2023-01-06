package com.shoppingmall.member.service;

import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.dto.response.MemberResponse;
import com.shoppingmall.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Nested
    @DisplayName("회원 가입 테스트 - Service")
    class Signup {
        @BeforeEach
        void clean() {
            memberRepository.deleteAll();
        }

        @Test
        @DisplayName("회원 가입 테스트 - 성공")
        void signup() {
            // given
            MemberSignup memberSignup = MemberSignup.builder()
                    .name("회원이름")
                    .email("yhwjd99@gmail.com")
                    .password("1234")
                    .age(25)
                    .build();


            // when
            MemberResponse memberResponse = memberService.save(memberSignup);

            // then
            assertThat(memberRepository.count()).isEqualTo(1);
            assertThat(memberResponse.getName()).isEqualTo("회원이름");
            assertThat(memberResponse.getEmail()).isEqualTo("yhwjd99@gmail.com");
            assertThat(memberResponse.getPassword()).isEqualTo("1234");
            assertThat(memberResponse.getAge()).isEqualTo(25);
        }
    }
}