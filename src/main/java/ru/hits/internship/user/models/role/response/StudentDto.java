package ru.hits.internship.user.models.role.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Модель студента")
public record StudentDto(
        @Schema(description = "Идентификатор роли")
        UUID id,

        @Schema(description = "Идентификатор пользователя")
        UUID userId,

        @Schema(description = "Идентификатор группы")
        UUID groupId
) {
}
