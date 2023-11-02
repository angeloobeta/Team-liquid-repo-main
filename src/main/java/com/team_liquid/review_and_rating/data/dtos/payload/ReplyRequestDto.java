package com.team_liquid.review_and_rating.data.dtos.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@Builder
public class ReplyRequestDto {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    private String name;
    @NotBlank(message = "This field can not be empty or blank")
    private String feedback;
}
