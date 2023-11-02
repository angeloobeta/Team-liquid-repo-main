package com.team_liquid.review_and_rating.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewExceptionResponse {

    private Map<String, String> data;
    private String message;
    private HttpStatus status;

}
