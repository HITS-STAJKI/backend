package ru.hits.internship.user.models.role.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Модель создания роли куратора")
public record CuratorCreateDto(
        @Schema(description = "Идентификатор пользователя")
        @NotNull
        UUID userId,

        @Schema(description = "Идентификатор компании")
        @NotNull
        UUID companyId
) {}