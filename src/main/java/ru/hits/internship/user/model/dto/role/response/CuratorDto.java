package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Модель куратора")
public record CuratorDto(
        @Schema(description = "Идентификатор роли")
        UUID id,

        @Schema(description = "Идентификатор пользователя")
        UUID userId,

        @Schema(description = "Идентификатор компании")
        UUID companyId
) {}