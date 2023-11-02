package com.team_liquid.review_and_rating.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Change GenerationType to AUTO
    private String id; // Use Long for the ID field
    private String productId;
    private Integer oneStar = 0; // Initialize all rating fields to 0
    private Integer twoStar = 0;
    private Integer threeStar = 0;
    private Integer fourStar = 0;
    private Integer fiveStar = 0;
    private Integer totalRating = 0;
    private Integer numberOfRating = 0;

    public void addRating(int rating) {
        switch (rating) {
            case 1 -> oneStar++;
            case 2 -> twoStar++;
            case 3 -> threeStar++;
            case 4 -> fourStar++;
            case 5 -> fiveStar++;
            default -> {
            }
            // Handle invalid rating if needed
        }
        totalRating += rating;
        numberOfRating++;
    }

    public double getAverageRating() {
        if (numberOfRating == 0) {
            return 0;
        }
        return (double) totalRating / numberOfRating;
    }
}

