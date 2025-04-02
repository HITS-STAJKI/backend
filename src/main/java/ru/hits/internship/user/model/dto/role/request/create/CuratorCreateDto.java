package ru.hits.internship.user.model.dto.role.request.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Модель для создания куратора")
public record CuratorCreateDto(
        @Schema(description = "Идентификатор пользователя")
        @NotNull
        UUID userId,

        @Schema(description = "Идентификатор компании")
        @NotNull
        UUID companyId
) {}