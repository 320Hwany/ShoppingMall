package com.shoppingmall.service;

import com.shoppingmall.domain.Review;
import com.shoppingmall.exception.e404.ReviewNotFoundException;
import com.shoppingmall.repository.review.ReviewRepository;
import com.shoppingmall.request.review.ReviewSave;
import com.shoppingmall.request.review.ReviewSearch;
import com.shoppingmall.request.review.ReviewUpdate;
import com.shoppingmall.response.ReviewResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
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
            ReviewSave reviewSave = ReviewSave.builder()
                    .title("제목입니다.")
                    .content("내용입니다.")
                    .rating(3)
                    .build();

            // when
            ReviewResponse reviewResponse = reviewService.reviewSave(reviewSave);

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
            assertThrows(ReviewNotFoundException.class,
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

    @Nested
    @DisplayName("리뷰 수정 테스트 - Service")
    class UpdateReview {
        @BeforeEach
        void clean() {
            reviewRepository.deleteAll();
        }

        @Test
        @DisplayName("수정할 리뷰가 존재하고 조건에 만족하면 리뷰가 수정됩니다.")
        void updateReviewSuccess() {
            // given
            Review review = Review.builder()
                    .title("제목입니다.")
                    .content("내용입니다.")
                    .rating(3)
                    .build();

            reviewRepository.save(review);

            ReviewUpdate reviewUpdate = ReviewUpdate.builder()
                    .title("제목 수정입니다.")
                    .content("내용 수정입니다.")
                    .rating(5)
                    .build();

            // when
            reviewService.updateReview(review.getId(), reviewUpdate);

            // then
            assertThat(reviewRepository.count()).isEqualTo(1);
            assertThat(review.getTitle()).isEqualTo("제목 수정입니다.");
            assertThat(review.getContent()).isEqualTo("내용 수정입니다.");
            assertThat(review.getRating()).isEqualTo(5);
        }

        @Test
        @DisplayName("수정할 리뷰가 존재하지 않으면 예외를 발생시킵니다.")
        void NotFoundReview() {
            // given
            ReviewUpdate reviewUpdate = ReviewUpdate.builder()
                    .title("제목 수정입니다.")
                    .content("내용 수정입니다.")
                    .rating(5)
                    .build();

            // expected
            assertThrows(ReviewNotFoundException.class,
                    () -> reviewService.updateReview(1L, reviewUpdate));
        }
    }

    @Nested
    @DisplayName("리뷰 삭제 테스트 - Service")
    class DeleteReview {
        @BeforeEach
        void clean() {
            reviewRepository.deleteAll();
        }

        @Test
        @DisplayName("삭제 요청한 리뷰가 존재하면 리뷰가 삭제됩니다.")
        void deleteReviewSuccess() {
            // given
            Review review = Review.builder()
                    .title("제목입니다.")
                    .content("내용입니다.")
                    .rating(3)
                    .build();

            reviewRepository.save(review);

            // when
            reviewService.deleteReview(review.getId());

            // then
            assertThat(reviewRepository.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("삭제 요청한 리뷰가 존재하지 않으면 오류 메세지를 보여줍니다.")
        void deleteReviewFail() {
            // expected
            assertThrows(ReviewNotFoundException.class,
                    () -> reviewService.deleteReview(1L));
        }
    }
}