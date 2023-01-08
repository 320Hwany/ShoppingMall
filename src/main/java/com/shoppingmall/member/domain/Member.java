package com.shoppingmall.member.domain;

import com.shoppingmall.member.dto.request.MemberSignup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String password;

    private Integer age;

    @Builder
    public Member(String name, String email, String password, Integer age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public Member(MemberSignup memberSignup) {
        this.name = memberSignup.getName();
        this.email = memberSignup.getEmail();
        this.password = memberSignup.getPassword();
        this.age = memberSignup.getAge();
    }
}
