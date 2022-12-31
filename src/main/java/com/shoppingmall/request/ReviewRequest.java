package com.shoppingmall.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor // @Build 쓸 때 없어서 테스트 코드에서 오류 발생
public class ReviewRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Min(value = 0, message = "0이상의 수를 입력해주세요.") @Max(value = 5, message = "5이하의 수를 입력해주세요.")
    private int rating;

    @Builder
    public ReviewRequest(String title, String content, int rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
}
