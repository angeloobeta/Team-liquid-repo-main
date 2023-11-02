package com.team_liquid.review_and_rating.service;

import com.team_liquid.review_and_rating.data.repositories.ReviewRepository;
import com.team_liquid.review_and_rating.services.ReviewService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @InjectMocks
    private ReviewService reviewService;
    @Mock
    private ReviewRepository reviewRepository;
}
