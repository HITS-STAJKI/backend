package ru.hits.internship.user.model.dto.role.filter;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record StudentFilter(
        @Schema(description = "ФИО", defaultValue = "Иванов Иван")
        String fullName,
        @Schema(description = "Статус нахождения студента в академе", defaultValue = "false")
        Boolean isAcadem,
        @Schema(description = "Статус выпуска студента", defaultValue = "false")
        Boolean isGraduated,
        @Schema(description = "Идентификаторы потоков")
        List<UUID> groupIds,
        @Schema(description = "Идентификаторы компаний-партнеров")
        List<UUID> companyIds,
        @Schema(description = "Находятся на практике/не находятся", defaultValue = "null")
        Boolean isOnPractice,
        @Schema(description = "Наличие заявки на практику", defaultValue = "null")
        Boolean hasPracticeRequest,
        @Schema(description = "Приступили/не приступили к собеседованиям", defaultValue = "null")
        Boolean hasInterviews,
        @Schema(description = "Идентификаторы стеков")
        List<UUID> stackIds,
        @Schema(description = "Время последнего захода в систему", example = "2025-06-04T15:30:00")
        LocalDateTime lastLogin
) {
}