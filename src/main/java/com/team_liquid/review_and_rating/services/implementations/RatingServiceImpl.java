package com.team_liquid.review_and_rating.services.implementations;

import com.team_liquid.review_and_rating.data.dtos.response.ApiResponseDto;
import com.team_liquid.review_and_rating.data.dtos.response.RatingResponseDto;
import com.team_liquid.review_and_rating.data.entities.Rating;
import com.team_liquid.review_and_rating.data.repositories.RatingRepository;
import com.team_liquid.review_and_rating.exceptions.ReviewException;
import com.team_liquid.review_and_rating.services.RatingService;
import com.team_liquid.review_and_rating.utils.RatingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RatingUtils ratingUtils;

    @Override
    public ApiResponseDto<RatingResponseDto> productRating(String productId) {
        Rating ratingResponse= ratingRepository.findByProductId(productId);
        if(ratingResponse == null){
            throw new ReviewException("ProductId doesn't exist");
        }
        RatingResponseDto ratingResponseDto = ratingUtils.mapRatingToDto(ratingResponse);
        return new ApiResponseDto<>("Successfully fetched", 200, ratingResponseDto);
    }
}
