package com.shoppingmall.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponse {

    private String title;

    private String content;

    private int rating;

    @Builder
    public ReviewResponse(String title, String content, int rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
}
