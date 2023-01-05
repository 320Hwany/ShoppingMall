package com.shoppingmall.review.repository.review;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.shoppingmall.review.domain.Review;
import com.shoppingmall.review.dto.request.ReviewSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.shoppingmall.review.domain.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Review> getReviewsByLatest(ReviewSearch reviewSearch) {
        return jpaQueryFactory.selectFrom(review)
                .limit(reviewSearch.getLimit())
                .offset(reviewSearch.getOffset())
                .orderBy(review.id.desc())
                .fetch();
    }

    @Override
    public List<Review> getReviewsByLowRating(ReviewSearch reviewSearch) {
        return jpaQueryFactory.selectFrom(review)
                .limit(reviewSearch.getLimit())
                .offset(reviewSearch.getOffset())
                .orderBy(review.rating.asc())
                .fetch();
    }

    @Override
    public List<Review> getReviewsByHighRating(ReviewSearch reviewSearch) {
        return jpaQueryFactory.selectFrom(review)
                .limit(reviewSearch.getLimit())
                .offset(reviewSearch.getOffset())
                .orderBy(review.rating.desc())
                .fetch();
    }
}
