package com.shoppingmall.review.repository.review;

import com.shoppingmall.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
}
