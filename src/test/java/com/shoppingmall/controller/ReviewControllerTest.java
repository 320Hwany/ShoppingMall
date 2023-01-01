package com.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.Review;
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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Nested
    @DisplayName("리뷰 저장 테스트")
    class SaveReview {
        @BeforeEach
        void clean() {
            reviewRepository.deleteAll();
        }
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

            assertThat(reviewRepository.count()).isEqualTo(1);
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

            assertThat(reviewRepository.count()).isEqualTo(0);
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

            assertThat(reviewRepository.count()).isEqualTo(0);
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

            assertThat(reviewRepository.count()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("리뷰 조회 테스트")
    class ReadReview {
        @BeforeEach
        void clean() {
            reviewRepository.deleteAll();
        }

        @Test
        @DisplayName("게시글이 있으면 조회에 성공합니다. - 단건 조회")
        void readReviewSuccess() throws Exception {
            // given
            Review review = Review.builder()
                    .title("리뷰 제목")
                    .content("리뷰 내용")
                    .rating(3)
                    .build();

            reviewRepository.save(review);
            ReviewRequest reviewRequest = new ReviewRequest(review);

            // expected
            mockMvc.perform(get("/review/{reviewId}", review.getId())
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("리뷰 제목"))
                    .andExpect(jsonPath("$.content").value("리뷰 내용"))
                    .andExpect(jsonPath("$.rating").value("3"))
                    .andDo(print());
        }

        @Test
        @DisplayName("게시글이 없으면 조회에 실패합니다. - 단건 조회")
        void readReviewFail() throws Exception {
            // expected
            mockMvc.perform(get("/review/{reviewId}", 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value("404"))
                    .andExpect(jsonPath("$.message").value("게시글을 찾을 수 없습니다."))
                    .andDo(print());
        }

        @Test
        @DisplayName("원하는 페이지를 보여줍니다. 한 페이지당 10개의 게시글이 있습니다.")
        void readReviewsSuccess() throws Exception {
            // given
            List<Review> reviews = IntStream.rangeClosed(1, 30)
                    .mapToObj(i -> Review.builder()
                            .title("제목입니다 " + i)
                            .content("내용입니다 " + i)
                            .rating((int) (Math.random() * 6))
                            .build())
                    .collect(Collectors.toList());

            reviewRepository.saveAll(reviews);
            // expected
            mockMvc.perform(get("/reviews?page=2")
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(10)))
                    .andExpect(jsonPath("$[0].title").value("제목입니다 20"))
                    .andExpect(jsonPath("$[0].content").value("내용입니다 20"))
                    .andDo(print());
        }
    }
}