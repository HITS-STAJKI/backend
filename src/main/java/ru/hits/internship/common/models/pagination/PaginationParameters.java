package ru.hits.internship.common.models.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record PaginationParameters(
        @Schema(description = "Номер страницы")
        @Min(1)
        int page,

        @Schema(description = "Количество элементов страницы")
        @Min(1)
        int size
) {}
