package com.shoppingmall.member.repository;

import com.shoppingmall.member.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository {

    Optional<Session> findByAccessToken(String accessToken);

    void save(Session session);

    void deleteAll();
}
