package com.shoppingmall.auth.repository;

import com.shoppingmall.auth.domain.LoginToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginTokenRepository extends JpaRepository<LoginToken, Long> {

    Optional<LoginToken> findByAccessToken(String accessToken);
}
