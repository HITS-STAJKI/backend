package ru.hits.internship.user.models.role.request.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Модель создания роли студента")
public record StudentCreateDto(
        @Schema(description = "Идентификатор пользователя")
        @NotNull
        UUID userId,

        @Schema(description = "Идентификатор группы")
        @NotNull
        UUID groupId
) {}