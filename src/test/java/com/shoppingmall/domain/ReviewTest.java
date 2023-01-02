package com.shoppingmall.domain;

import com.shoppingmall.request.review.ReviewUpdate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ReviewTest {

    @Test
    @DisplayName("리뷰 수정 테스트 - Domain")
    void updateReview() {
        // given
        Review review = Review.builder()
                .title("제목 수정전")
                .content("내용 수정전")
                .rating(3)
                .build();

        ReviewUpdate reviewUpdate = ReviewUpdate.builder()
                .title("제목 수정후")
                .content("내용 수정후")
                .rating(5)
                .build();

        review.update(reviewUpdate);

        assertThat(review.getTitle()).isEqualTo("제목 수정후");
        assertThat(review.getContent()).isEqualTo("내용 수정후");
        assertThat(review.getRating()).isEqualTo(5);
    }
}