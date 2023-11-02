package com.team_liquid.review_and_rating.data.dtos.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class ReplyResponseDto {
    private String replyId;
    private String name;
    private String message;
    private String createAt;
}
