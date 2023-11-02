package com.team_liquid.review_and_rating.services;

import com.team_liquid.review_and_rating.data.dtos.response.ApiResponseDto;
import com.team_liquid.review_and_rating.data.dtos.response.RatingResponseDto;

public interface RatingService {
    ApiResponseDto<RatingResponseDto> productRating(String productId);
}
