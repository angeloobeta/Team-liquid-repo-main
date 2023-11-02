package com.team_liquid.review_and_rating.utils;

import com.team_liquid.review_and_rating.data.dtos.response.ReplyResponseDto;
import com.team_liquid.review_and_rating.data.entities.Reply;
import org.springframework.stereotype.Component;

@Component
public class ReplyUtils {
    public static String REPLY_SENT = "Reply as been sent";
    public ReplyResponseDto replyMapToDto(Reply reply){
        return ReplyResponseDto.builder()
                .message(reply.getMessage())
                .name(reply.getName())
                .replyId(reply.getId())
                .createAt(reply.getCreatedAt())
                .build();
    }
}
