package com.shoppingmall.member.controller;

import com.shoppingmall.member.dto.request.MemberLogin;
import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.dto.response.MemberResponse;
import com.shoppingmall.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.shoppingmall.member.domain.Session.setCookie;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public MemberResponse signup(@RequestBody @Valid MemberSignup memberSignup) {
        MemberResponse memberResponse = memberService.signup(memberSignup);
        return memberResponse;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid MemberLogin memberLogin) {
        String accessToken = memberService.login(memberLogin);
        ResponseCookie cookie = setCookie(accessToken);

        return ResponseEntity.ok()
                .header(SET_COOKIE, cookie.toString())
                .build();
    }
}
