package com.team_liquid.review_and_rating.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class ReviewException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ReviewException(){
        this("Error Processing Request!");
    }

    public ReviewException(String message){
        super(message);
    }

    public ReviewException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }

}
