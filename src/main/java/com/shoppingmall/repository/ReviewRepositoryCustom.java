package com.shoppingmall.repository;

import com.shoppingmall.domain.Review;
import com.shoppingmall.request.ReviewSearch;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> getReviews(ReviewSearch reviewSearch);
}
