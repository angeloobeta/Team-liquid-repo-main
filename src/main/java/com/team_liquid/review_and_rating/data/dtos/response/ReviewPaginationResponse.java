package com.team_liquid.review_and_rating.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPaginationResponse<T> {
    private T content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;

}
