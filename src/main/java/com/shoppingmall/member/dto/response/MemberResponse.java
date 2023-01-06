package com.shoppingmall.member.dto.response;

import com.shoppingmall.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private String name;
    private String email;
    private String password;

    private Integer age;

    public MemberResponse(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.age = member.getAge();
    }
}
