package com.team_liquid.review_and_rating.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDto<T> {

    private String message;
    private Integer statusCode;
    private T data;


}
