package com.team_liquid.review_and_rating.data.dtos.response;

import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PageInfoDto {
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;
}
