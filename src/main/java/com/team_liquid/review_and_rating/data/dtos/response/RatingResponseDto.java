package com.team_liquid.review_and_rating.data.dtos.response;

import lombok.*;

@Builder
@Setter @Getter @AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDto {
    private String id;
    private String productId;
    private Integer oneStar;
    private Integer twoStar;
    private Integer threeStar;
    private Integer fourStar;
    private Integer fiveStar;
    private Integer totalRating;
    private double averageRating;
    private Integer numberOfRating;

}
