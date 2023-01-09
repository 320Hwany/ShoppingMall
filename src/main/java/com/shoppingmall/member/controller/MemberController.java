package com.shoppingmall.member.controller;

import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.domain.Session;
import com.shoppingmall.member.dto.request.MemberLogin;
import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.dto.request.MemberUpdate;
import com.shoppingmall.member.dto.response.MemberResponse;
import com.shoppingmall.member.service.MemberService;
import com.shoppingmall.member.service.SessionService;
import com.shoppingmall.utils.AccessTokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final SessionService sessionService;
    private final AccessTokenHolder accessTokenHolder;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid MemberSignup memberSignup) {
        memberService.signup(memberSignup);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid MemberLogin memberLogin) {
        Member member = memberService.getMember(memberLogin);
        Session session = sessionService.makeSession(member, accessTokenHolder);
        ResponseCookie cookie = session.setCookie();

        return ResponseEntity.ok()
                .header(SET_COOKIE, cookie.toString())
                .build();
    }

    @PatchMapping("/member/{memberId}")
    public void updateMember(@PathVariable Long memberId, @RequestBody @Valid MemberUpdate memberUpdate) {
        memberService.updateMember(memberId, memberUpdate);
    }

    @DeleteMapping("/member/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}
