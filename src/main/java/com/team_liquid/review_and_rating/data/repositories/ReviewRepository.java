package com.team_liquid.review_and_rating.data.repositories;

import com.team_liquid.review_and_rating.data.entities.Rating;
import com.team_liquid.review_and_rating.data.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,String> {
    List<Review> findAllByProductId(String productId);
    Page<Review> findByProductId(String productId, Pageable pageable);
    Page<Review> findByProductIdAndRating(String productId, Integer rating, Pageable pageable);

    Review findReviewByProductId(String productId);
}
