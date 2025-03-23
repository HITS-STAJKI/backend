package ru.hits.internship.user.models.role.request.edit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Модель для изменения студента")
public record StudentEditDto(
        @Schema(description = "Идентификатор группы")
        @NotNull
        UUID groupId
) {
}
