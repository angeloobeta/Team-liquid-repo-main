package com.team_liquid.review_and_rating.utils;

import com.team_liquid.review_and_rating.data.dtos.response.ReviewResponseDto;
import com.team_liquid.review_and_rating.data.entities.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.team_liquid.review_and_rating.utils.DateUtils.saveDate;

@Component
public class ReviewUtils {

    public final static String REVIEW_SUCCESSFULLY = "Review Successfully created";


    public Pageable pageable(Integer pageNumber, Integer pageSize){
        return PageRequest.of(pageNumber, pageSize);
    }

    public ReviewResponseDto toResponseDto (Review userView){
        return  ReviewResponseDto.builder()
                .createdAt(saveDate(userView.getCreatedAt()))
                .updatedAt(saveDate(userView.getUpdatedAt()))
                .customerName(userView.getCustomerName())
                .rating(userView.getRating())
                .description(userView.getDescription())
                .productId(userView.getProductId())
                .reviewId(userView.getId())
                .isHelpFul(String.valueOf(userView.getIsHelpful()))
                .reply(userView.getReply())
                .build();
    }
}
