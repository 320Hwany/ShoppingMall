package com.shoppingmall.member.dto.request;

import com.shoppingmall.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class MemberSignup {

    @NotBlank(message = "회원 이름을 입력해주세요")
    private String name;

    @Email(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @Min(value = 0, message = "0 이상의 수를 입력해주세요")
    private Integer age;

    @Builder
    public MemberSignup(String name, String email, String password, Integer age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }
}
