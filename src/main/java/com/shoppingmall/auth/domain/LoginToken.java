package com.shoppingmall.auth.domain;

import com.shoppingmall.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static java.util.UUID.*;
import static javax.persistence.GenerationType.*;

@Getter
@NoArgsConstructor
@Entity
public class LoginToken {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "login_token_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String accessToken;

    @Builder
    public LoginToken(Member member) {
        this.member = member;
        this.accessToken = randomUUID().toString();
    }
}
