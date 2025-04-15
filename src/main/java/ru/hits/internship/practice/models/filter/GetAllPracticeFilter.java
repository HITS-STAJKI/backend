package ru.hits.internship.practice.models.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Фильтры для получения всех практик")
public class GetAllPracticeFilter {
    @Schema(description = "Имя студента", example = "Васильев")
    private String studentName;
    @Schema(description = "Список идентификаторов групп")
    private List<UUID> groupIds;
    @Schema(description = "Идентификатор компании-партнера", example = "f39b02d1-8bff-4055-90aa-09819522b1d9")
    private UUID companyId;
    @Schema(description = "Флаг выбора практик с прикрепленным отчетом или без. null - если все", example = "null")
    private Boolean hasReport;
    @Schema(description = "Флаг выдачи практик с подтвержденным отчетом", example = "null")
    private Boolean isReportApproved;
    @Schema(description = "Флаг выдачи архивных данных", example = "null")
    private Boolean isArchived;
    @Schema(description = "Флаг подтвержденных практик", example = "null")
    private Boolean isPracticeApproved;
}
