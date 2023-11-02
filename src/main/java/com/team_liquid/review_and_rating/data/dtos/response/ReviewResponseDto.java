package com.team_liquid.review_and_rating.data.dtos.response;

import com.team_liquid.review_and_rating.data.entities.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private String productId;
    private String reviewId;

    private String customerName;
    private String description;
    private String isHelpFul;
    private Integer rating;
    private Reply reply;
    private String updatedAt;

    private String createdAt;
}
