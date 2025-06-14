package ru.hits.internship.statistics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Статистика по найденным студентам")
public class StatisticsResponse {
    @Schema(description = "Количество найденных студентов")
    private long count;
}
