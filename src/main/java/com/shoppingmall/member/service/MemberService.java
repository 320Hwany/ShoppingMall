package com.shoppingmall.member.service;

import com.shoppingmall.member.dto.request.MemberLogin;
import com.shoppingmall.member.exception.UnauthorizedException;
import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.domain.Session;
import com.shoppingmall.member.dto.request.MemberSignup;
import com.shoppingmall.member.dto.response.MemberResponse;
import com.shoppingmall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse save(MemberSignup memberSignup) {
        Member member = new Member(memberSignup);
        memberRepository.save(member);
        return new MemberResponse(member);
    }

    @Transactional
    public String login(MemberLogin memberLogin) {
        Member member = memberRepository.findByEmailAndPassword(memberLogin.getEmail(), memberLogin.getPassword())
                .orElseThrow(UnauthorizedException::new);
        Session session = member.addSession();

        return session.getAccessToken();
    }
}
