package ru.hits.internship.common.models.pagination;

import org.springframework.data.domain.Page;

import java.util.List;

public record PagedListDto<T>(List<T> items, PageDto page) {

    public PagedListDto(Page<T> pagedList) {
        this(
                pagedList.getContent(),
                new PageDto(
                        pagedList.getContent().size(),
                        pagedList.getTotalPages(),
                        pagedList.getNumber()
                )
        );
    }
}
