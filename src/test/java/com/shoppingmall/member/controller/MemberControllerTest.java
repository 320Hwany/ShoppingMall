package com.shoppingmall.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.dto.request.MemberLogin;
import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.dto.request.MemberUpdate;
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
import org.springframework.transaction.annotation.Transactional;

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
        @Transactional
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
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value("404"))
                    .andExpect(jsonPath("$.message").value("회원을 찾을 수 없습니다."))
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("회원 수정 테스트 - Controller")
    class Update {
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
        @DisplayName("조건에 맞으면 회원이 수정됩니다 - 성공")
        void update() throws Exception {
            // given
            MemberUpdate memberUpdate = MemberUpdate.builder()
                    .name("회원 이름")
                    .email("yhwjd99@gmail.com")
                    .password("1234")
                    .age(25)
                    .build();

            memberRepository.save(member);
            String json = objectMapper.writeValueAsString(memberUpdate);

            // expected
            mockMvc.perform(patch("/member/{memberId}", member.getId())
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andDo(print());
        }

        @Test
        @DisplayName("조건에 맞지 않으면 오류메세지를 보여줍니다- 실패")
        void updateFail() throws Exception {
            // given
            MemberUpdate memberUpdate = MemberUpdate.builder()
                    .name("")
                    .email("yhwjd99")
                    .password("1234")
                    .age(25)
                    .build();

            memberRepository.save(member);
            String json = objectMapper.writeValueAsString(memberUpdate);

            // expected
            mockMvc.perform(patch("/member/{memberId}", member.getId())
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.name").value("회원 이름을 입력해주세요"))
                    .andExpect(jsonPath("$.validation.email").value("이메일을 입력해주세요"))
                    .andDo(print());
        }

        @Test
        @DisplayName("조건에 맞지만 회원이 존재하지 않으면 오류메세지를 보여줍니다- 실패")
        void MemberNotFound() throws Exception {
            // given
            MemberUpdate memberUpdate = MemberUpdate.builder()
                    .name("회원 이름")
                    .email("yhwjd99@gmail.com")
                    .password("1234")
                    .age(25)
                    .build();

            String json = objectMapper.writeValueAsString(memberUpdate);

            // expected
            mockMvc.perform(patch("/member/{memberId}", 1L)
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value("404"))
                    .andExpect(jsonPath("$.message").value("회원을 찾을 수 없습니다."))
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("회원 삭제 테스트 - Controller")
    class Delete {
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
        @DisplayName("회원이 존재하면 회원이 삭제됩니다 - 성공")
        void deleteMember() throws Exception {
            // given
            memberRepository.save(member);

            // expected
            mockMvc.perform(delete("/member/{memberId}", member.getId()))
                    .andExpect(status().isOk())
                    .andDo(print());
        }

        @Test
        @DisplayName("회원이 존재하지 않으면 회원이 삭제되지 않습니다 - 실패")
        void deleteMemberFail() throws Exception {
            // expected
            mockMvc.perform(delete("/member/{memberId}", 1L))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }
    }
}