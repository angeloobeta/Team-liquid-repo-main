package com.team_liquid.review_and_rating.data.repositories;

import com.team_liquid.review_and_rating.data.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,String> {
    Rating findByProductId(String productId);
}
