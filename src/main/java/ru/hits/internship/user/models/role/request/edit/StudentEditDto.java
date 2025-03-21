package ru.hits.internship.user.models.role.request.edit;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.UUID;

@Schema(description = "Модель изменения роли студента")
public record StudentEditDto(
        @Schema(description = "Идентификатор группы")
        @NotNull
        UUID groupId,

        @NotNull
        @UniqueElements
        @ArraySchema(schema = @Schema(implementation = UUID.class, description = "id собеседования студента"))
        List<UUID> interviewIds
) {}
