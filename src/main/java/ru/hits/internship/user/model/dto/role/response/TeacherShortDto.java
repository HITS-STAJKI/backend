package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Модель преподавателя")
public record TeacherShortDto(
        @NotNull
        @Schema(description = "Идентификатор роли")
        UUID id
) {}