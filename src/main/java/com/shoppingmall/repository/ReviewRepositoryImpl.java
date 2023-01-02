package com.shoppingmall.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.shoppingmall.domain.Review;
import com.shoppingmall.request.review.ReviewSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.shoppingmall.domain.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Review> getReviews(ReviewSearch reviewSearch) {
        return jpaQueryFactory.selectFrom(review)
                .limit(reviewSearch.getLimit())
                .offset(reviewSearch.getOffset())
                .orderBy(review.id.desc())
                .fetch();
    }
}
