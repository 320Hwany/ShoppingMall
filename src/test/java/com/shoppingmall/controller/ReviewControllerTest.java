package com.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.repository.ReviewRepository;
import com.shoppingmall.request.ReviewRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    void clean() {
        reviewRepository.deleteAll();
    }

    @Nested
    @DisplayName("리뷰 저장 테스트")
    class Review {
        @Test
        @DisplayName("조건에 만족하면 리뷰가 저장됩니다.")
        void saveSuccess() throws Exception {
            // given
            ReviewRequest reviewRequest = ReviewRequest.builder()
                    .title("리뷰 제목")
                    .content("리뷰 내용")
                    .rating(3)
                    .build();

            String json = objectMapper.writeValueAsString(reviewRequest);

            // expected
            mockMvc.perform(post("/review-save")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("리뷰 제목"))
                    .andExpect(jsonPath("$.content").value("리뷰 내용"))
                    .andExpect(jsonPath("$.rating").value(3))
                    .andDo(print());
        }

        @Test
        @DisplayName("조건에 만족하지 않으면 오류 메세지를 보여줍니다 - 제목 오류")
        void saveFailCauseTitle() throws Exception {
            // given
            ReviewRequest reviewRequest = ReviewRequest.builder()
                    .title("")
                    .content("리뷰 내용")
                    .rating(3)
                    .build();

            String json = objectMapper.writeValueAsString(reviewRequest);

            // expected
            mockMvc.perform(post("/review-save")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.title").value("제목을 입력해주세요."))
                    .andDo(print());
        }

        @Test
        @DisplayName("조건에 만족하지 않으면 오류 메세지를 보여줍니다 - 내용 오류")
        void saveFailCauseContent() throws Exception {
            // given
            ReviewRequest reviewRequest = ReviewRequest.builder()
                    .title("리뷰 제목")
                    .content("")
                    .rating(3)
                    .build();

            String json = objectMapper.writeValueAsString(reviewRequest);

            // expected
            mockMvc.perform(post("/review-save")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.content").value("내용을 입력해주세요."))
                    .andDo(print());
        }

        @Test
        @DisplayName("조건에 만족하지 않으면 오류 메세지를 보여줍니다 - 별점 오류")
        void saveFailCauseRating() throws Exception {
            // given
            ReviewRequest reviewRequest = ReviewRequest.builder()
                    .title("리뷰 제목")
                    .content("리뷰 내용")
                    .rating(6)
                    .build();

            String json = objectMapper.writeValueAsString(reviewRequest);

            // expected
            mockMvc.perform(post("/review-save")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.rating").value("5이하의 수를 입력해주세요."))
                    .andDo(print());
        }
    }
}