package com.shoppingmall.member.service;

import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.dto.request.MemberLogin;
import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.dto.response.MemberResponse;
import com.shoppingmall.member.exception.UnauthorizedException;
import com.shoppingmall.member.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            MemberResponse memberResponse = memberService.signup(memberSignup);

            // then
            assertThat(memberRepository.count()).isEqualTo(1);
            assertThat(memberResponse.getName()).isEqualTo("회원이름");
            assertThat(memberResponse.getEmail()).isEqualTo("yhwjd99@gmail.com");
            assertThat(memberResponse.getPassword()).isEqualTo("1234");
            assertThat(memberResponse.getAge()).isEqualTo(25);
        }
    }

    @Nested
    @DisplayName("회원 로그인 테스트 - Service")
    @Transactional
    class Login {
        private Member member = Member.builder()
                .name("회원 이름")
                .email("yhwjd99@gmail.com")
                .password("1234")
                .age(25)
                .sessionList(new ArrayList<>())
                .build();

        @BeforeEach
        void clean() {
            memberRepository.deleteAll();
        }

        @Test
        @DisplayName("회원 로그인 테스트 - 성공")
        void login() {
            // given
            MemberLogin memberLogin = MemberLogin.builder()
                    .email("yhwjd99@gmail.com")
                    .password("1234")
                    .build();

            memberRepository.save(member);

            // when
            String accessToken = memberService.login(memberLogin);

            // then
            assertThat(member.getSessionList().size()).isEqualTo(1);
            assertThat(member.getSessionList().get(0).getAccessToken()).isEqualTo(accessToken);
        }

        @Test
        @DisplayName("회원 정보가 일치하지 않으면 로그인을 할 수 없습니다. - 실패")
        void loginFail() {
            // given
            MemberLogin memberLogin = MemberLogin.builder()
                    .email("yhwjd99@gmail.com")
                    .password("1234567890")
                    .build();

            memberRepository.save(member);

            // expected
            assertThrows(UnauthorizedException.class,
                    () -> memberService.login(memberLogin));
        }
    }
}