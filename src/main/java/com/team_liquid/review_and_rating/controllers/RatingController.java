package com.team_liquid.review_and_rating.controllers;

import com.team_liquid.review_and_rating.services.RatingService;
import com.team_liquid.review_and_rating.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class RatingController {

    private final RatingService ratingService;

    @Operation(summary = "Fetch the rating metrics  for a product via it's productId")
    @GetMapping("/products/{productId}/rating")
    public ResponseEntity<?> productRating(
            @Parameter(description = "The productId is required")
            @PathVariable("productId") String productId
    ){
        log.info("{}",productId);
        return ResponseEntity.ok(ratingService.productRating(productId));
    }
}
