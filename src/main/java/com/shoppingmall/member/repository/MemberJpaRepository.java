package com.shoppingmall.member.repository;

import com.shoppingmall.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndPassword(String email, String password);
}
