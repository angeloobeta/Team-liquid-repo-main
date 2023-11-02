package com.team_liquid.review_and_rating.data.dtos.response;

import lombok.*;

@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse<T> {
    private PageInfoDto meta;
    private T items;
}
