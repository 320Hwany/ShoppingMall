package com.shoppingmall.auth.controller;

import com.shoppingmall.auth.dto.request.LoginRequest;
import com.shoppingmall.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Duration;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequest loginRequest) {
        String accessToken = authService.login(loginRequest);

        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
                .domain("localhost")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .maxAge(Duration.ofDays(30))
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok()
                .header(SET_COOKIE, cookie.toString())
                .build();
    }
}
