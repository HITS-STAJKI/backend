package ru.hits.internship.common.models.pagination;

import io.swagger.v3.oas.annotations.media.Schema;

public record PageDto(
        @Schema(description = "Количество элементов текущей страницы")
        int size,

        @Schema(description = "Номер текущей страницы")
        int currentPage,

        @Schema(description = "Общее количество страниц")
        int totalPages,

        @Schema(description = "Общее количество элементов")
        long totalElements
) {}
