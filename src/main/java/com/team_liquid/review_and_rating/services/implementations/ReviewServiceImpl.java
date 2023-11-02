package com.team_liquid.review_and_rating.services.implementations;

import com.team_liquid.review_and_rating.data.dtos.payload.ReplyRequestDto;
import com.team_liquid.review_and_rating.data.dtos.payload.ReviewRequestDto;
import com.team_liquid.review_and_rating.data.dtos.response.*;
import com.team_liquid.review_and_rating.data.entities.Rating;
import com.team_liquid.review_and_rating.data.entities.Reply;
import com.team_liquid.review_and_rating.data.entities.Review;
import com.team_liquid.review_and_rating.data.repositories.RatingRepository;
import com.team_liquid.review_and_rating.data.repositories.ReplyRepository;
import com.team_liquid.review_and_rating.data.repositories.ReviewRepository;
import com.team_liquid.review_and_rating.exceptions.ReviewException;
import com.team_liquid.review_and_rating.services.ReviewService;
import com.team_liquid.review_and_rating.utils.DateUtils;
import com.team_liquid.review_and_rating.utils.PaginationUtils;
import com.team_liquid.review_and_rating.utils.ReplyUtils;
import com.team_liquid.review_and_rating.utils.ReviewUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RatingRepository ratingRepository;
    private final ReviewUtils reviewUtils;
    private final ReplyUtils replyUtils;
    private final ReplyRepository replyRepository;
    private final PaginationUtils paginationUtils;
    private final DateUtils dateUtils;


    @Override
    public ApiResponseDto<ReviewResponseDto> addReviewView(String productId, ReviewRequestDto reviewRequestDto) {
         Review   review = new Review();
            review.setProductId(productId);
            review.setCustomerName(reviewRequestDto.getCustomerName());
            review.setIsHelpful(0L);
            review.setDescription(reviewRequestDto.getDescription());
            review.setUpdatedAt(LocalDateTime.now());
            review.setCreatedAt(LocalDateTime.now());
            review.setRating(reviewRequestDto.getRateNo());
            review = reviewRepository.save(review);
        // Check if the productId already exist
        Rating ratingEntity = ratingRepository.findByProductId(productId);
        if (ratingEntity == null) {
            ratingEntity = new Rating();
            ratingEntity.setProductId(productId);
        }
        // add the rating and update other parameters
        ratingEntity.addRating(reviewRequestDto.getRateNo());
        ratingRepository.save(ratingEntity);


        ReviewResponseDto reviewResponseDto = reviewUtils.toResponseDto(review);
        return new ApiResponseDto<>(ReviewUtils.REVIEW_SUCCESSFULLY, 201, reviewResponseDto);
    }


    @Override
    public ApiResponseDto<String> deleteReView(String reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
            log.info("Review with id: {} successfully deleted", reviewId);
            return new ApiResponseDto<>("Review Successfully deleted ", 200, reviewId);

        }
        return new ApiResponseDto<>("The review with Id " + reviewId + " doesn't exist", 404, reviewId);
    }


    public ApiResponseDto<PaginationResponse<?>> viewReviewAndRatingInShop(String productId, Integer pageNumber, Integer pageSize) {
        Page<Review> reviews = reviewRepository.findByProductId(productId,
                reviewUtils.pageable(pageNumber, pageSize));
        List<ReviewResponseDto> reviewResponse = reviews.stream()
                .map(reviewUtils::toResponseDto).toList();
        PaginationResponse<Object> paginationResponse = paginationUtils.mapPaginationResponseToDto(reviews, reviewResponse);
        return new ApiResponseDto<>(ReviewUtils.REVIEW_SUCCESSFULLY, 200, paginationResponse);
    }


    @Override
    public ApiResponseDto<PaginationResponse<?>> viewReviewAndRatingInMarketPlace(String productId, Integer pageNumber, Integer pageSize) {
        Page<Review> reviews = reviewRepository.findByProductId(productId,
                reviewUtils.pageable(pageNumber, pageSize));
        List<ReviewResponseDto> reviewResponse = reviews.stream()
                .map(reviewUtils::toResponseDto).toList();
        PaginationResponse<Object> paginationResponse = paginationUtils.mapPaginationResponseToDto(reviews, reviewResponse);
        return new ApiResponseDto<>(ReviewUtils.REVIEW_SUCCESSFULLY, 200, paginationResponse);

    }

    @Override
    public ApiResponseDto<PaginationResponse<?>> filterReviewAndResponseInShopForProducts(String productId, Integer rating, Integer pageNumber, Integer pageSize) {
        Page<Review> reviews = reviewRepository.findByProductIdAndRating(productId, rating,
                reviewUtils.pageable(pageNumber, pageSize));
        List<ReviewResponseDto> reviewResponse = reviews.stream()
                .map(reviewUtils::toResponseDto).toList();
        PaginationResponse<Object> paginationResponse = paginationUtils.mapPaginationResponseToDto(reviews, reviewResponse);
        return new ApiResponseDto<>(ReviewUtils.REVIEW_SUCCESSFULLY, 200, paginationResponse);
    }


    @Override
    public ApiResponseDto<PaginationResponse<?>> filterReviewsRatingsInTheMarketplaceForProducts(Integer rating, String productId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = reviewUtils.pageable(pageNumber, pageSize);
        Page<Review> reviews = reviewRepository.findByProductIdAndRating(productId, rating,
                pageable);
        List<ReviewResponseDto> reviewResponse = reviews.stream()
                .map(reviewUtils::toResponseDto).toList();
        PaginationResponse<Object> paginationResponse = paginationUtils.mapPaginationResponseToDto(reviews, reviewResponse);
        return new ApiResponseDto<>(ReviewUtils.REVIEW_SUCCESSFULLY, 200, paginationResponse);
    }

    @Override
    public ApiResponseDto<ReviewResponseDto> findReviewById(String reviewId) {
        Review findReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException("This review doesn't exist", HttpStatus.BAD_REQUEST));
        ReviewResponseDto responseDto = reviewUtils.toResponseDto(findReview);
        return new ApiResponseDto<>(ReviewUtils.REVIEW_SUCCESSFULLY, 200, responseDto);
    }


    @Override
    public ApiResponseDto<ReplyResponseDto> replyReview(String reviewId, ReplyRequestDto replyRequest) {
        Review savedReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException("The reviewId doesn't exist pls check and try again", HttpStatus.BAD_REQUEST));
        Reply replyFeed = new Reply();
        replyFeed.setMessage(replyRequest.getFeedback());
        replyFeed.setName(replyRequest.getName());
        replyFeed.setCreatedAt(DateUtils.saveDate(LocalDateTime.now()));
        Reply reply = replyRepository.save(replyFeed);
        // save the message to the reviewRepository
        savedReview.setReply(reply);
        // save the replyRepository
        reviewRepository.save(savedReview);
        ReplyResponseDto responseDto = replyUtils.replyMapToDto(reply);
        return new ApiResponseDto<>(ReplyUtils.REPLY_SENT, 200, responseDto);
    }
    @Override
    public ApiResponseDto<PaginationResponse<?>>  sortReview(int pageNumber, int pageSize, String productId, String sortingParameter) {
        Pageable pageable;
        Sort sort;
        if(sortingParameter.equalsIgnoreCase("topReview")) {
            sort = Sort.by("isHelpful").ascending();
            pageable = PageRequest.of(pageNumber, pageSize, sort);
            PaginationResponse reviewPaginationResponse = reviewPaginationResponse(pageable, productId);
            return new ApiResponseDto<>(ReplyUtils.REPLY_SENT, 200, reviewPaginationResponse);

        }
        sort= sortingParameter.equalsIgnoreCase("newest") ? Sort.by("createdAt").ascending()
            : Sort.by("createdAt").descending();

            pageable = PageRequest.of(pageNumber, pageSize, sort);
            PaginationResponse reviewPaginationResponse=reviewPaginationResponse(pageable,productId);
            return new ApiResponseDto<>(ReplyUtils.REPLY_SENT, 200, reviewPaginationResponse);
    }

    @Override
    public ApiResponseDto<String> isReviewHelpFull(String reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow( () ->
                 new ReviewException("Review doesn't exist"));
        review.addReviewHelpful();
        Review saveReview = reviewRepository.save(review);
        return new ApiResponseDto<>("Feedback Added", 200, saveReview.getIsHelpful().toString());
    }


    private PaginationResponse reviewPaginationResponse(Pageable pageable, String productId) {
        Page<Review> reviewPage = reviewRepository.findByProductId(productId,pageable);
        List<ReviewResponseDto> content = reviewPage.getContent().stream()
                .map(reviewUtils::toResponseDto)
                .collect(Collectors.toList());
        return paginationUtils.mapPaginationResponseToDto(reviewPage, content);
    }

}
