package com.team_liquid.review_and_rating.data.dtos.payload;


import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ReviewRequestDto {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    private String customerName;
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    private String description;
    @Min(value = 1, message = "Invalid rate: Rate shouldn't be less than 1")
    @Max(value = 5, message = "Invalid rate: Rate shouldn't exceed 5")
    private Integer rateNo;
}
