package com.team_liquid.review_and_rating.data.dtos.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class RatingRequestDto {
    String productId;
    String oneStar;
    String twoStar;
    String threeStar;
    String fourStart;
    String fiveStart;
}
