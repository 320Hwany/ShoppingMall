package com.shoppingmall.member.domain;

import com.shoppingmall.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseCookie;

import javax.persistence.*;

import java.time.Duration;

import static java.util.UUID.*;
import static javax.persistence.GenerationType.*;

@Getter
@NoArgsConstructor
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "session_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String accessToken;

    @Builder
    public Session(Member member) {
        this.member = member;
        this.accessToken = randomUUID().toString();
    }

    public static ResponseCookie setCookie(String accessToken) {
        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
                .domain("localhost")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .maxAge(Duration.ofDays(30))
                .sameSite("Strict")
                .build();
        return cookie;
    }
}
