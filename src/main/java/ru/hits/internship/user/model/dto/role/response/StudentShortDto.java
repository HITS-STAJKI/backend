package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.hits.internship.group.dto.GroupShortDto;

import java.util.UUID;

@Schema(description = "Модель студента")
public record StudentShortDto(
        @NotNull
        @Schema(description = "Идентификатор роли")
        UUID id,

        @NotNull
        Boolean isAcadem,

        @NotNull
        Boolean isGraduated,

        @NotNull
        GroupShortDto group
) {}