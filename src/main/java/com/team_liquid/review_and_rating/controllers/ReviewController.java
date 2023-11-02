package com.team_liquid.review_and_rating.controllers;

import com.team_liquid.review_and_rating.data.dtos.payload.ReplyRequestDto;
import com.team_liquid.review_and_rating.data.dtos.payload.ReviewRequestDto;
import com.team_liquid.review_and_rating.data.dtos.response.ReviewResponseDto;
import com.team_liquid.review_and_rating.services.RatingService;
import com.team_liquid.review_and_rating.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final RatingService ratingService;
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReviewResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The productId you are trying to use to add a review doesn't exist, check the name and try again.", content = { @Content(schema = @Schema()) })
    })
    @Operation(summary = "A user tries to make a review via the productId")
    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<?> addReviewAndRating(
            @Parameter(description = "The productId is required")
            @PathVariable String productId,
            @RequestBody @Valid ReviewRequestDto reviewRequestDto ){
        return ResponseEntity.ok(reviewService.addReviewView(productId, reviewRequestDto));
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReviewResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The reviewId you are trying to access doesn't exist, check the Id again and try again.", content = { @Content(schema = @Schema()) })
    })
    @Operation(summary = "Find a review via the reviewId")
    @GetMapping("/products/reviews/{reviewId}")
    public ResponseEntity<?> findReviewById(
            @Parameter(description = "The productId is required")
            @PathVariable String reviewId){
        return ResponseEntity.ok(reviewService.findReviewById(reviewId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReviewResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The reviewId doesn't exist, check the name and try again.", content = { @Content(schema = @Schema()) })
    })
    @Operation(summary = "A user tries to delete a review via the reviewId")
    @DeleteMapping("/products/{reviewId}")
    public ResponseEntity<?> deleteReviewAndRating(
            @Parameter(description = "The reviewId is required")
            @PathVariable String reviewId){
        return ResponseEntity.ok(reviewService.deleteReView(reviewId));}

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReviewResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The productId was not found, check the name and try again.", content = { @Content(schema = @Schema()) })
    })
    @Operation(summary = "A user tries to fetch all the review for a product via it's productId")
    @GetMapping("/shop/{productId}/reviews")
        public ResponseEntity<?> viewReviewInShop (
            @Parameter(description = "The productId is required")
            @PathVariable("productId") String productId,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ){
        log.info("Did we pass hereor not");
        return ResponseEntity.ok(reviewService.viewReviewAndRatingInShop(productId, pageNumber, pageSize));
        }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReviewResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The productId you are trying to access was not found, check the name and try again.", content = { @Content(schema = @Schema()) })
    })
    @Operation(summary = "Fetch all review for a product in market via it's productId")
    @GetMapping("/marketplace/products/{productId}/reviews")
    public ResponseEntity<?> viewReviewInMarket (
            @Parameter(description = "The productId is required")
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @PathVariable("productId") String productId){
        return ResponseEntity.ok(reviewService.viewReviewAndRatingInMarketPlace(productId, pageNumber, pageSize));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReviewResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The productId was not found, check the productId and try again.", content = { @Content(schema = @Schema()) })
    })
    @Operation(summary = "Filter the review and rating in the shop for products via productId and rating")
    @GetMapping("/shop/products/{productId}/reviews/rating")
    public ResponseEntity<?> filterReviewAndRating(
            @Parameter(description = "The productId is required")
            @PathVariable("productId") String productId,
            @RequestParam Integer rating,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
            ){
        return ResponseEntity.ok(reviewService.filterReviewAndResponseInShopForProducts(productId, rating, pageNumber, pageSize));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReviewResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The product was not found, check the productId and try again.", content = { @Content(schema = @Schema()) })
    })
    @Operation(summary = "Filters the review and rating in the market place for products via the productId")
    @GetMapping("/marketplace/products/{productId}/reviews/rating")
    public ResponseEntity<?> filterReviewsRatingsInTheMarketplaceForProducts(
            @Parameter(description = "The productId is required")
            @PathVariable("productId") String productId,
            @RequestParam("rating") Integer rating,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
            ){
        return ResponseEntity.ok(reviewService.filterReviewsRatingsInTheMarketplaceForProducts(rating, productId, pageNumber, pageSize));
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReviewResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The review you are trying to reply to was not found, check the name and try again.", content = { @Content(schema = @Schema()) })
    })
    @Operation(summary = "Reply a review that has been made by a customer via it's reviewId")
    @PostMapping("/shop/reviews/{reviewId}")
    public ResponseEntity<?> replyReviews(
            @Parameter(description = "The reviewId is required")
            @PathVariable String reviewId,
            @RequestBody @Valid ReplyRequestDto replyRequest){
        return ResponseEntity.ok(reviewService.replyReview(reviewId, replyRequest));
    }


    @Operation(summary = "Fetch review by either: oldest; newest; topRiew")
    @GetMapping("/products/{productId}/reviews/sort")
    public ResponseEntity<?> sortReview(
            @Parameter(description = "The productId and sorting parameter are required")
            @PathVariable("productId") String productId,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortingParameter") String sortingParameter
    ){
        return ResponseEntity.ok(reviewService.sortReview(pageNumber, pageSize,productId,sortingParameter));
    }


    // cart checkout Giant
    // Netwon
//    Ismail Akintunde
//    order_item

    //
//    O_I.product_id === productId
//    O_I.customer_id === userid
//    O_I.status === "completed"

//    Akuya Ekorot8:20 PM
//    https://staging.zuri.team/api/auth/api/auth/login
//    Za_Genius18:21 PM
//    https://staging.zuri.team/api/auth/api/authorize

    @Operation(summary = "Add a feedback count to a review that's useful")
    @PostMapping("/products/review/{reviewId}")
    public ResponseEntity<?> isReviewHelpfull(
            @Parameter(description = "The productId is required")
            @PathVariable("reviewId") String reviewId
    ){
        log.info("{}",reviewId);
        return ResponseEntity.ok(reviewService.isReviewHelpFull(reviewId));
    }
}
//https://www.postman.com/gold-spaceship-212378/workspace/zuriportfolio-authentication-service/collection/29474066-7d09ca68-e8d0-4198-a5be-0f34142ab84f?action=share&creator=29474066
//https://dbdiagram.io/d/updated-zuridb-design-651e81beffbf5169f015d9a3