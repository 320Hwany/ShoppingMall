package com.shoppingmall.member.service;

import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.domain.Session;
import com.shoppingmall.member.repository.SessionRepository;
import com.shoppingmall.utils.AccessTokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    @Transactional
    public Session makeSession(Member member, AccessTokenHolder accessTokenHolder) {
        Session session = Session.builder()
                .member(member)
                .accessTokenHolder(accessTokenHolder)
                .build();
        sessionRepository.save(session);

        return session;
    }
}
