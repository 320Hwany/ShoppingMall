package com.shoppingmall.member.service;

import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.dto.request.MemberLogin;
import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.dto.request.MemberUpdate;
import com.shoppingmall.member.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

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
            memberService.signup(memberSignup);
            // then
            assertThat(memberRepository.getByEmailAndPassword("yhwjd99@gmail.com", "1234"))
                    .isNotNull();
        }
    }

    @Test
    @DisplayName("회원정보 일치하는 회원정보 가져오기 - 성공")
    void getMember() {
        // given
        Member member = Member.builder()
                .name("회원 이름")
                .email("yhwjd99@gmail.com")
                .password("1234")
                .age(25)
                .build();

        MemberLogin memberLogin = MemberLogin.builder()
                .email("yhwjd99@gmail.com")
                .password("1234")
                .build();

        memberRepository.save(member);

        // when
        Member getMember = memberService.getMember(memberLogin);

        // then
        assertThat(member).isEqualTo(getMember);
    }

    @Test
    @DisplayName("회원 정보가 일치하는 회원 정보 수정하기 - 성공")
    void updateMember() {
        //given
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

        memberRepository.save(member);

        // when
        memberService.updateMember(member.getId(), memberUpdate);

        // then
        Member getMember = memberRepository
                .getByEmailAndPassword(memberUpdate.getEmail(), memberUpdate.getPassword());
        assertThat(getMember.getName()).isEqualTo(memberUpdate.getName());
        assertThat(getMember.getEmail()).isEqualTo(memberUpdate.getEmail());
        assertThat(getMember.getPassword()).isEqualTo(memberUpdate.getPassword());
        assertThat(getMember.getAge()).isEqualTo(memberUpdate.getAge());
    }

    @Test
    @DisplayName("회원 삭제하기 - 성공")
    void deleteMember() {
        //given
        Member member = Member.builder()
                .name("회원 이름")
                .email("yhwjd99@gmail.com")
                .password("1234")
                .age(25)
                .build();

        memberRepository.save(member);

        // when
        memberService.deleteMember(member.getId());

        // then
        assertThat(memberRepository.findByEmailAndPassword("yhwjd99@gmail.com", "1234"))
                .isNotPresent();
    }
}