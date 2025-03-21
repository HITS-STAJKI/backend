package ru.hits.internship.user.models.role.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Модель куратора")
public record CuratorResponseDto(
        @Schema(description = "Идентификатор роли", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
        UUID id,

        @Schema(description = "Идентификатор компании", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
        UUID companyId
) {}