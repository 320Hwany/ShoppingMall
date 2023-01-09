package com.shoppingmall.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.dto.request.MemberLogin;
import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.repository.MemberRepository;
import com.shoppingmall.member.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("회원 가입 테스트 - Controller")
    class Signup {
        @BeforeEach
        void clean() {
            sessionRepository.deleteAll();
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
        }
    }

    @Nested
    @DisplayName("회원 로그인 테스트 - Controller")
    class Login {
        private Member member = Member.builder()
                .name("회원 이름")
                .email("yhwjd99@gmail.com")
                .password("1234")
                .age(25)
                .build();

        @BeforeEach
        void clean() {
            sessionRepository.deleteAll();
            memberRepository.deleteAll();
        }

        @Test
        @DisplayName("회원 로그인 테스트 - 성공")
        void login() throws Exception {
            // given
            MemberLogin memberLogin = MemberLogin.builder()
                    .email("yhwjd99@gmail.com")
                    .password("1234")
                    .build();

            memberRepository.save(member);
            String json = objectMapper.writeValueAsString(memberLogin);

            // expected
            mockMvc.perform(post("/login")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(header().exists("Set-Cookie"))
                    .andDo(print());
        }

        @Test
        @DisplayName("조건에 맞지 않으면 회원 로그인이 되지 않습니다 - 실패")
        void loginFailByValid() throws Exception {
            // given
            MemberLogin memberLogin = MemberLogin.builder()
                    .email("yhwjd99@gmail.com")
                    .password("")
                    .build();

            memberRepository.save(member);
            String json = objectMapper.writeValueAsString(memberLogin);

            // expected
            mockMvc.perform(post("/login")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.password").value("비밀번호를 입력해주세요"))
                    .andDo(print());
        }

        @Test
        @DisplayName("조건에는 맞지만 회원정보가 일치하지 않으면 로그인이 되지 않습니다 - 실패")
        void loginFailByNotMatch() throws Exception {
            // given
            MemberLogin memberLogin = MemberLogin.builder()
                    .email("yhwjd99@gmail.com")
                    .password("123456789")
                    .build();

            memberRepository.save(member);
            String json = objectMapper.writeValueAsString(memberLogin);

            // expected
            mockMvc.perform(post("/login")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isUnauthorized())
                    .andExpect(jsonPath("$.code").value("401"))
                    .andExpect(jsonPath("$.message").value("로그인 후 이용해주세요"))
                    .andDo(print());
        }
    }
}