package com.shoppingmall.member.service;

import com.shoppingmall.member.dto.request.MemberLogin;
import com.shoppingmall.member.dto.request.MemberUpdate;
import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.dto.request.MemberSignup;
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
    public void signup(MemberSignup memberSignup) {
        Member member = new Member(memberSignup);
        memberRepository.save(member);
    }

    @Transactional
    public Member getMember(MemberLogin memberLogin) {
        Member member = memberRepository.getByEmailAndPassword(memberLogin.getEmail(), memberLogin.getPassword());
        return member;
    }

    @Transactional
    public void updateMember(Long id, MemberUpdate memberUpdate) {
        Member member = memberRepository.getById(id);
        member.update(memberUpdate);
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.getById(id);
        memberRepository.delete(member);
    }
}
