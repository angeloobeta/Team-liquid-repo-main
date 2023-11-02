package com.team_liquid.review_and_rating.services.implementations;

import com.team_liquid.review_and_rating.data.dtos.payload.ReplyRequestDto;
import com.team_liquid.review_and_rating.data.dtos.payload.ReviewRequestDto;
import com.team_liquid.review_and_rating.data.dtos.response.ReplyResponseDto;
import com.team_liquid.review_and_rating.data.dtos.response.ReviewResponseDto;
import com.team_liquid.review_and_rating.data.entities.Review;
import com.team_liquid.review_and_rating.data.repositories.ReviewRepository;
import com.team_liquid.review_and_rating.services.ReviewService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    private ReviewRequestDto reviewRequestDto;

    private ReplyRequestDto replyRequestDto;

    @BeforeEach
    public void setUp(){
        reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setRateNo(3);
        reviewRequestDto.setDescription("I love this product");
        reviewRequestDto.setCustomerName("Jennifer Musah");

        replyRequestDto = ReplyRequestDto.builder()
                .name("Mercy")
                .feedback("e ma bi nu")
                .build();
    }
    @Test
    void addReviewView() {
       assertEquals( "Review Successfully created", reviewService.addReviewView("567439", reviewRequestDto).getMessage());
       assertEquals(1, reviewRepository.findAll().size());
    }

    @Test
    void deleteReView() {
        ReviewResponseDto review =  reviewService.addReviewView("567439", reviewRequestDto).getData();
        assertEquals(1, reviewRepository.findAll().size());
        assertEquals("Review Successfully deleted ",reviewService.deleteReView(review.getReviewId()).getMessage());
        assertEquals(0, reviewRepository.findAll().size());

    }
   @Test
    void TestThatReviewWouldNotBeDeletedIfWrongIdIsProvided() {
        ReviewResponseDto review =  reviewService.addReviewView("567439", reviewRequestDto).getData();
        assertEquals(1, reviewRepository.findAll().size());
        assertEquals("The review with Id wrong id doesn't exist",reviewService.deleteReView("wrong id").getMessage());
        assertEquals(1, reviewRepository.findAll().size());

    }

    @Test
    void viewReviewAndRatingInShop() {
//         addReviewView();
//        List<ReviewResponseDto>  reviewResponseDtoList = reviewService.viewReviewAndRatingInShop("125638399", 1, 10).getData();
//      assertFalse(reviewResponseDtoList.isEmpty());
//      assertEquals(1, reviewResponseDtoList.size());

    }

    @Test
    void viewReviewAndRatingInMarketPlace() {
//        assertEquals( "Review Successfully created", reviewService.addReview("567439", reviewRequestDto).getMessage());
//        assertEquals(1, reviewRepository.findAll().size());
//        List<ReviewResponseDto>  reviewResponseDtoList = reviewService.viewReviewAndRatingInMarketPlace("567439", 1, 10).getData();
//        assertFalse(reviewResponseDtoList.isEmpty());
//        assertEquals(1, reviewResponseDtoList.size());
    }

    @Test
    void filterReviewAndResponseInShopForProducts() {
//        addReviewView();
//                List<ReviewResponseDto>  reviewResponseDtoList = reviewService.filterReviewAndResponseInShopForProducts("567439", 3, 1, 10).getData();
//              assertFalse(reviewResponseDtoList.isEmpty());
//              assertEquals(1, reviewResponseDtoList.size());
    }

    @Test
    void filterReviewsRatingsInTheMarketplaceForProducts() {
    }

    @Test
    void findReviewById() {
        ReviewResponseDto review =  reviewService.addReviewView("567439", reviewRequestDto).getData();
        ReviewResponseDto review1 = reviewService.findReviewById(review.getReviewId()).getData();
        assertEquals(review.getRating(), review1.getRating());
    }

    @Test
    void fetchAllReview() {
//                addReviewView();
//                        List<ReviewResponseDto>  reviewResponseDtoList = reviewService.fetchAllReview(1,10).getData();
//                      assertFalse(reviewResponseDtoList.isEmpty());
//                      assertEquals(1, reviewResponseDtoList.size());
    }

    @Test
    void replyReview() {
        ReviewResponseDto review =  reviewService.addReviewView("567439", reviewRequestDto).getData();
       assertEquals("Reply as been sent",reviewService.replyReview(review.getReviewId(),replyRequestDto).getMessage());
    }
}
