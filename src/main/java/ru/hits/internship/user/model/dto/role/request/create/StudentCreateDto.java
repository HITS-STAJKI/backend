package ru.hits.internship.user.model.dto.role.request.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Модель для создания студента")
public record StudentCreateDto(
        @Schema(description = "Идентификатор группы")
        @NotNull
        UUID groupId
) {}