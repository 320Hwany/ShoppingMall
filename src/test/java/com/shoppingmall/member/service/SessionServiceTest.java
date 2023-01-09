package com.shoppingmall.member.service;

import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.domain.Session;
import com.shoppingmall.member.repository.SessionRepository;
import com.shoppingmall.utils.AccessTokenHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class SessionServiceTest {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("세션 생성 테스트 - Service")
    void makeSession() {
        // given
        Member member = Member.builder()
                .name("회원 이름")
                .email("yhwjd99@gmail.com")
                .password("1234")
                .age(25)
                .build();

        // when
        Session session = sessionService.makeSession(member, new TestAccessTokenHolder());

        // then
        assertThat(session.getMember()).isEqualTo(member);
        assertThat(session.getAccessToken()).isEqualTo(TestAccessTokenHolder.MESSAGE);
    }

    private class TestAccessTokenHolder implements AccessTokenHolder {

        private static final String MESSAGE = "testToken";

        @Override
        public String getAccessToken() {
            return MESSAGE;
        }
    }
}