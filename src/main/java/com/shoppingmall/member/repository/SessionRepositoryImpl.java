package com.shoppingmall.member.repository;

import com.shoppingmall.member.domain.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SessionRepositoryImpl implements SessionRepository {

    private final SessionJpaRepository sessionJpaRepository;

    @Override
    public Optional<Session> findByAccessToken(String accessToken) {
        return sessionJpaRepository.findByAccessToken(accessToken);
    }

    @Override
    public void save(Session session) {
        sessionJpaRepository.save(session);
    }

    @Override
    public void deleteAll() {
        sessionJpaRepository.deleteAll();
    }
}
