package com.team_liquid.review_and_rating.utils;

import com.team_liquid.review_and_rating.data.dtos.response.PageInfoDto;
import com.team_liquid.review_and_rating.data.dtos.response.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtils {



    public PaginationResponse<Object> mapPaginationResponseToDto(Page pageInfo, Object data){
        // pageInfo builder
        PageInfoDto page = PageInfoDto.builder()
                .pageNo(pageInfo.getNumber())
                .totalElements(pageInfo.getTotalElements())
                .pageSize(pageInfo.getSize())
                .totalPages(pageInfo.getTotalPages())
                .isLastPage(pageInfo.isLast())
                .build();

        return PaginationResponse.builder()
                .meta(page)
                .items(data)
                .build();
    }
}
