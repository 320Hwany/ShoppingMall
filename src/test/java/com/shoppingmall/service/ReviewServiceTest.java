package com.shoppingmall.service;

import com.shoppingmall.domain.Review;
import com.shoppingmall.exception.e404.PostNotFoundException;
import com.shoppingmall.repository.ReviewRepository;
import com.shoppingmall.request.ReviewRequest;
import com.shoppingmall.request.ReviewSearch;
import com.shoppingmall.response.ReviewResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Nested
    @DisplayName("리뷰 저장 테스트 - Service")
    class SaveReview {
        @BeforeEach
        void clean() {
            reviewRepository.deleteAll();
        }
        @Test
        @DisplayName("리뷰 저장")
        void save() {
            // given
            ReviewRequest reviewRequest = ReviewRequest.builder()
                    .title("제목입니다.")
                    .content("내용입니다.")
                    .rating(3)
                    .build();

            // when
            ReviewResponse reviewResponse = reviewService.reviewSave(reviewRequest);

            // then
            assertThat(reviewRepository.count()).isEqualTo(1);
            assertThat(reviewResponse.getTitle()).isEqualTo("제목입니다.");
            assertThat(reviewResponse.getContent()).isEqualTo("내용입니다.");
            assertThat(reviewResponse.getRating()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("리뷰 조회 테스트 - Service")
    class ReadReview {
        @BeforeEach
        void clean() {
            reviewRepository.deleteAll();
        }

        @Test
        @DisplayName("리뷰 조회 성공 - 단건 조회")
        void readReviewSuccess() {
            // given
            Review review = Review.builder()
                    .title("제목입니다.")
                    .content("내용입니다.")
                    .rating(3)
                    .build();

            reviewRepository.save(review);

            // when
            ReviewResponse reviewResponse = reviewService.getReviewResponse(review.getId());

            // then
            assertThat(reviewResponse.getTitle()).isEqualTo("제목입니다.");
            assertThat(reviewResponse.getContent()).isEqualTo("내용입니다.");
            assertThat(reviewResponse.getRating()).isEqualTo(3);
        }

        @Test
        @DisplayName("리뷰 조회 실패 - 단건 조회")
        void readReviewFail() {
            // expected
            assertThrows(PostNotFoundException.class,
                    () -> reviewService.getReviewResponse(1L));
        }

        @Test
        @DisplayName("리뷰 조회 - 한 페이지 조회")
        void readReviews() {
            // given
            List<Review> reviews = IntStream.rangeClosed(1, 30)
                    .mapToObj(i -> Review.builder()
                            .title("제목입니다. " + i)
                            .content("내용입니다. " + i)
                            .rating((int) (Math.random() * 6))
                            .build())
                    .collect(Collectors.toList());

            reviewRepository.saveAll(reviews);

            ReviewSearch reviewSearch = ReviewSearch.builder()
                    .page(2)
                    .build();

            // when
            List<ReviewResponse> reviewsResponse = reviewService.getReviewsResponse(reviewSearch);

            // then
            assertThat(reviewsResponse.size()).isEqualTo(10);
            assertThat(reviewsResponse.get(0).getTitle()).isEqualTo("제목입니다. " + 20);
            assertThat(reviewsResponse.get(0).getContent()).isEqualTo("내용입니다. " + 20);
        }
    }
}