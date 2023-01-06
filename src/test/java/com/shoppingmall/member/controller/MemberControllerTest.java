package com.shoppingmall.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("회원 가입 테스트 - Controller")
    class Signup {
        @BeforeEach
        void clean() {
            memberRepository.deleteAll();
        }

        @Test
        @DisplayName("회원 가입 테스트 - 성공")
        void signup() throws Exception {
            // given
            MemberSignup memberSignup = MemberSignup.builder()
                    .name("회원이름")
                    .email("yhwjd99@gmail.com")
                    .password("1234")
                    .age(25)
                    .build();

            String json = objectMapper.writeValueAsString(memberSignup);

            // expected
            mockMvc.perform(post("/signup")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("회원이름"))
                    .andExpect(jsonPath("$.email").value("yhwjd99@gmail.com"))
                    .andExpect(jsonPath("$.password").value("1234"))
                    .andExpect(jsonPath("$.age").value(25))
                    .andDo(print());

            assertThat(memberRepository.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("조건에 맞지 않으면 회원 가입이 되지 않습니다. - 실패")
        void signupFail() throws Exception {
            // given
            MemberSignup memberSignup = MemberSignup.builder()
                    .name("회원 이름")
                    .email("hello world")
                    .password("1234")
                    .age(25)
                    .build();

            String json = objectMapper.writeValueAsString(memberSignup);

            // expected
            mockMvc.perform(post("/signup")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.email").value("이메일을 입력해주세요"))
                    .andDo(print());

            assertThat(memberRepository.count()).isEqualTo(0);
        }
    }
}