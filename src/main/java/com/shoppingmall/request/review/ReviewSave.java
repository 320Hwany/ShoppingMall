package com.shoppingmall.request.review;

import com.shoppingmall.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor // @Build 를 사용할 때 이게 없어서 테스트 코드에서 오류 발생
public class ReviewSave {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Min(value = 0, message = "0이상의 수를 입력해주세요.") @Max(value = 5, message = "5이하의 수를 입력해주세요.")
    private Integer rating;

    @Builder
    public ReviewSave(String title, String content, Integer rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public ReviewSave(Review review) {
        this.title = review.getTitle();
        this.content = review.getContent();
        this.rating = review.getRating();
    }

    public Review toEntity() {
        return Review.builder()
                .title(title)
                .content(content)
                .rating(rating)
                .build();
    }
}
