package ru.hits.internship.interview.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Фильтр для получения страницы отборов")
public class InterviewFilter {
    @Schema(description = "ФИ студента (разрешается частичное совпадение)", example = "Иванов Иван")
    private String studentName;

    @Schema(description = "Идентификатор компании-партнера", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID partnerId;

    @Schema(description = "Идентификатор стека", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID stackId;

    @Schema(description = "Идентификаторы ЯПов", example = "[\"3ea42ea8-5258-4086-a43f-113ff89577a1\"]")
    private List<UUID> languageIds;

    @Schema(description = "Идентификатор группы", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID studentGroupId;

    @Schema(description = "Статус отбора", example = "PENDING")
    private StatusEnum status;

    @Schema(description = "Время последнего обновления отборов (от)", example = "2023-10-01T10:00:00")
    private LocalDateTime modifiedAtFrom;

    @Schema(description = "Время последнего обновления отборов (до)", example = "2023-10-31T10:00:00")
    private LocalDateTime modifiedAtTo;
}
