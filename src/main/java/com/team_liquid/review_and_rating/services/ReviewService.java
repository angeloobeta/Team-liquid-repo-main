package com.team_liquid.review_and_rating.services;

import com.team_liquid.review_and_rating.data.dtos.payload.ReplyRequestDto;
import com.team_liquid.review_and_rating.data.dtos.payload.ReviewRequestDto;
import com.team_liquid.review_and_rating.data.dtos.response.*;


public interface ReviewService{
     ApiResponseDto<ReviewResponseDto> addReviewView(String productId, ReviewRequestDto reviewRequestDto);
     ApiResponseDto<String> deleteReView(String reviewId);
    ApiResponseDto<PaginationResponse<?>> viewReviewAndRatingInShop(String productId, Integer pageNumber, Integer pageSize);
    ApiResponseDto<PaginationResponse<?>> viewReviewAndRatingInMarketPlace(String productId, Integer pageNumber, Integer pageSize);
     ApiResponseDto<PaginationResponse<?>> filterReviewAndResponseInShopForProducts(String productId,Integer rating, Integer pageNumber, Integer pageSize);
    ApiResponseDto<PaginationResponse<?>> filterReviewsRatingsInTheMarketplaceForProducts(Integer rating, String productId, Integer pageNumber, Integer pageSize);

    ApiResponseDto<ReplyResponseDto>replyReview(String reviewId, ReplyRequestDto replyRequest);

   ApiResponseDto<ReviewResponseDto> findReviewById(String reviewId);

    ApiResponseDto<PaginationResponse<?>> sortReview(int pageNumber, int pageSize, String productId, String sortingParameter);

    ApiResponseDto<String> isReviewHelpFull(String reviewId);


}
