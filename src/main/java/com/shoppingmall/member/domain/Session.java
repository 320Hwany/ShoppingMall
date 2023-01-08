package com.shoppingmall.member.domain;

import com.shoppingmall.utils.AccessTokenHolder;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseCookie;

import javax.persistence.*;

import java.time.Duration;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Getter
@NoArgsConstructor
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "session_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String accessToken;

    @Builder
    public Session(Member member, AccessTokenHolder accessTokenHolder) {
        this.member = member;
        this.accessToken = accessTokenHolder.getAccessToken();
    }

    public ResponseCookie setCookie() {
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
