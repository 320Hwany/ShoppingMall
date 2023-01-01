package com.shoppingmall.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewSearch {

    private final Integer page;
    private final Integer limit = 10;

    @Builder
    public ReviewSearch(Integer page) {
        this.page = page;
    }

    public Integer getOffset() {
        return (Math.max(page - 1, 0) * limit);
    }
}
