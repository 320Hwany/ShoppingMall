package com.shoppingmall.item.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemSearch {

    private Integer page;
    private final Integer limit = 10;

    @Builder
    public ItemSearch(Integer page) {
        this.page = page;
    }

    public Integer getOffset() {
        return (Math.max(page - 1, 0) * limit);
    }
}
