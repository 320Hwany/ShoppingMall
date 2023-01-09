package com.shoppingmall.review.repository.review;

import com.shoppingmall.review.domain.Review;
import com.shoppingmall.review.dto.request.ReviewSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository {

    Review save(Review review);

    Review getById(Long id);

    List<Review> getReviewsByLatest(ReviewSearch reviewSearch);

    List<Review> getReviewsByHighRating(ReviewSearch reviewSearch);

    List<Review> getReviewsByLowRating(ReviewSearch reviewSearch);

    void delete(Review review);

    void deleteAll();

    void saveAll(List<Review> reviews);

    Long count();
}
