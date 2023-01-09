package com.shoppingmall.member.repository;

import com.shoppingmall.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findByEmailAndPassword(String email, String password) {
        return memberJpaRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public void deleteAll() {
        memberJpaRepository.deleteAll();
    }
}
