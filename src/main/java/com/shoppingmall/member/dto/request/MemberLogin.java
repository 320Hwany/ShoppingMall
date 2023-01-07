package com.shoppingmall.member.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class MemberLogin {

    @Email(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @Builder
    public MemberLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
