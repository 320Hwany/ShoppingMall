package com.shoppingmall.response;

import com.shoppingmall.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponse {

    private String title;

    private String content;

    private Integer rating;

    @Builder
    public ReviewResponse(String title, String content, Integer rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public ReviewResponse(Review review) {
        this.title = review.getTitle();
        this.content = review.getContent();
        this.rating = review.getRating();
    }
}
