package com.team_liquid.review_and_rating.utils;

import com.team_liquid.review_and_rating.data.dtos.response.RatingResponseDto;
import com.team_liquid.review_and_rating.data.entities.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingUtils {
    public RatingResponseDto mapRatingToDto(Rating rating){
        return RatingResponseDto.builder()
                .id(rating.getId())
                .productId(rating.getProductId())
                .oneStar(rating.getOneStar())
                .twoStar(rating.getTwoStar())
                .threeStar(rating.getThreeStar())
                .fourStar(rating.getFourStar())
                .fiveStar(rating.getFiveStar())
                .averageRating(rating.getAverageRating())
                .numberOfRating(rating.getNumberOfRating())
                .totalRating(rating.getTotalRating())
                .build();
    }
}
