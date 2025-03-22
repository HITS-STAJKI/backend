package ru.hits.internship.user.models.role.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(description = "Модель роли студента")
public record StudentResponseDto(
        @Schema(description = "Идентификатор роли")
        UUID id,

        @Schema(description = "Идентификатор пользователя")
        UUID userId,

        @Schema(description = "Идентификатор группы")
        UUID groupId,

        // TODO: [#3787] Заменить UUID на InterviewDto
        @ArraySchema(schema = @Schema(implementation = UUID.class, description = "Собеседование студента"))
        List<UUID> interviews
) {}
