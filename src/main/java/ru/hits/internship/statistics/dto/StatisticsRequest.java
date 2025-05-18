package ru.hits.internship.statistics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsRequest {
    @Schema(description = "Начало интересующего периода выборки", example = "2025-04-20T00:00:00")
    private LocalDateTime startDate;
    @Schema(description = "Конец интересующего периода выборки", example = "2025-10-23T23:59:59")
    private LocalDateTime endDate;
    @Schema(description = "Включить ли архив", example = "false")
    private Boolean includeArchived;
    @Schema(description = """
            Мапа фильтров.
            Ключом является название фильтра, а значение = массив значений фильтров
            """)
    Map<String, List<String>> requestData;
}
