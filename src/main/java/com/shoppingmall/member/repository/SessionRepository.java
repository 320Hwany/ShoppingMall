package com.shoppingmall.member.repository;

import com.shoppingmall.member.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByAccessToken(String accessToken);
}
