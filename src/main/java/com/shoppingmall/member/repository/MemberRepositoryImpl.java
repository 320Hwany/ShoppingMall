package com.shoppingmall.member.repository;

import com.shoppingmall.member.domain.Member;
import com.shoppingmall.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public Member getById(Long id) {
        Member member = memberJpaRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
        return member;
    }

    @Override
    public Optional<Member> findByEmailAndPassword(String email, String password) {
        return memberJpaRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Member getByEmailAndPassword(String email, String password) {
        Member member = memberJpaRepository.findByEmailAndPassword(email, password)
                .orElseThrow(MemberNotFoundException::new);
        return member;
    }

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public void deleteAll() {
        memberJpaRepository.deleteAll();
    }

    @Override
    public void delete(Member member) {
        memberJpaRepository.delete(member);
    }

    @Override
    public Long count() {
        return memberJpaRepository.count();
    }
}
