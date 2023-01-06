package com.shoppingmall.member.controller;

import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.dto.response.MemberResponse;
import com.shoppingmall.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public MemberResponse signup(@RequestBody @Valid MemberSignup memberSignup) {
        MemberResponse memberResponse = memberService.save(memberSignup);
        return memberResponse;
    }
}
