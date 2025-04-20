package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.hits.internship.group.dto.GroupShortDto;
import ru.hits.internship.user.model.dto.user.UserShortDto;

import java.util.UUID;

@Schema(description = "Модель студента")
public record StudentDto(
        @NotNull
        @Schema(description = "Идентификатор роли")
        UUID id,

        @NotNull
        Boolean isAcadem,

        @NotNull
        Boolean isGraduated,

        @NotNull
        UserShortDto user,

        @NotNull
        GroupShortDto group
) {}
