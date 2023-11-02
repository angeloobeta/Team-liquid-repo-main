package com.team_liquid.review_and_rating.controller;

import com.team_liquid.review_and_rating.data.entities.Review;
import com.team_liquid.review_and_rating.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
public class ReviewControllerTest {
    @MockBean
    private ReviewService reviewService;
    private Review newReview;
    private Review newReviews;
    private Review newRevied;

    @BeforeEach
    void init(){
        newReview=new Review();
        newReviews=new Review();
        newRevied=new Review();
    }
}
