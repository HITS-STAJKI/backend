package ru.hits.internship.group.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Модель группы")
public record GroupShortDto(
        @Schema(description = "Идентификатор группы")
        UUID id,

        @Schema(description = "Номер группы", example = "972202")
        String number
) {}
