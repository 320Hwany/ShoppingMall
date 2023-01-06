package com.shoppingmall.auth.service;

import com.shoppingmall.auth.domain.LoginToken;
import com.shoppingmall.auth.dto.request.LoginRequest;
import com.shoppingmall.auth.exception.UnauthorizedException;
import com.shoppingmall.auth.repository.AuthRepository;
import com.shoppingmall.auth.repository.LoginTokenRepository;
import com.shoppingmall.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final LoginTokenRepository loginTokenRepository;

    @Transactional
    public String login(LoginRequest loginRequest) {
        Member member = authRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                .orElseThrow(UnauthorizedException::new);
        LoginToken loginToken = member.addLoginToken();
        loginTokenRepository.save(loginToken);

        return loginToken.getAccessToken();
    }
}
