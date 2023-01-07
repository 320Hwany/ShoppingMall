package com.shoppingmall.member.domain;

import com.shoppingmall.member.dto.request.MemberSignup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
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

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Session> sessionList = new ArrayList<>();

    @Builder
    public Member(String name, String email, String password, Integer age, List<Session> sessionList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.sessionList = sessionList;
    }

    public Member(MemberSignup memberSignup) {
        this.name = memberSignup.getName();
        this.email = memberSignup.getEmail();
        this.password = memberSignup.getPassword();
        this.age = memberSignup.getAge();
    }

    public Session addSession() {
        Session session = Session.builder()
                .member(this)
                .build();
        sessionList.add(session);

        return session;
    }
}
