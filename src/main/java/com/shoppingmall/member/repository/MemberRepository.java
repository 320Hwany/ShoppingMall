package com.shoppingmall.member.repository;

import com.shoppingmall.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {

    Optional<Member> findById(Long id);

    Member getById(Long id);

    Optional<Member> findByEmailAndPassword(String email, String password);

    Member getByEmailAndPassword(String email, String password);

    void save(Member member);

    void deleteAll();

    void delete(Member member);

    Long count();
}
