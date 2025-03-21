package ru.hits.internship.user.models.role.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(description = "Модель студента")
public record StudentResponseDto(
        @Schema(description = "Идентификатор роли", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
        UUID id,

        @Schema(description = "Идентификатор группы", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
        UUID groupId,

        // TODO: [#3787] Заменить UUID на InterviewDto
        @ArraySchema(schema = @Schema(implementation = UUID.class, description = "Собеседование студента"))
        List<UUID> interviews
) {}
