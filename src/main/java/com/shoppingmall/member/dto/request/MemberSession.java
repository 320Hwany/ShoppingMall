package com.shoppingmall.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSession {

    private Long id;

    @Builder
    public MemberSession(Long id) {
        this.id = id;
    }
}
