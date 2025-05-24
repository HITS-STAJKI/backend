package ru.hits.internship.statistics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Schema(description = "Статистика по найденным студентам")
public class StatisticsResponse {
    @Schema(description = "Количество найденных студентов")
    private long count;
}
