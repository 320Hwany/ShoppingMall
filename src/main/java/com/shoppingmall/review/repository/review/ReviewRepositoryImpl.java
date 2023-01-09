package com.shoppingmall.review.repository.review;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.shoppingmall.review.domain.Review;
import com.shoppingmall.review.dto.request.ReviewSearch;
import com.shoppingmall.review.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shoppingmall.review.domain.QReview.review;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public Review save(Review review) {
        return reviewJpaRepository.save(review);
    }

    @Override
    public Review getById(Long id) {
        return reviewJpaRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);
    }

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

    @Override
    public void delete(Review review) {
        reviewJpaRepository.delete(review);
    }

    @Override
    public void deleteAll() {
        reviewJpaRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Review> reviews) {
        reviewJpaRepository.saveAll(reviews);
    }

    @Override
    public Long count() {
        return reviewJpaRepository.count();
    }
}
