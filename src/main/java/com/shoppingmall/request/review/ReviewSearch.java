package com.shoppingmall.request.review;

import lombok.Builder;
import lombok.Getter;

@Getter // 최대 글에 따라서 조회할 수 있는 페이지에 한계가 있는데 예외처리를 어디서 해줘야하나.. @Valid 는 아니고
// 그렇다고 findAll 로 다 가져와서 체크해야되나...
public class ReviewSearch {

    private Integer page;
    private final Integer limit = 10;

    @Builder
    public ReviewSearch(Integer page) {
        this.page = page;
    }

    public Integer getOffset() {
        return (Math.max(page - 1, 0) * limit);
    }
}
