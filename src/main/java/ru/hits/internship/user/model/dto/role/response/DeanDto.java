package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.hits.internship.user.model.dto.user.UserShortDto;

import java.util.UUID;

@Schema(description = "Модель деканата")
public record DeanDto(
        @NotNull
        @Schema(description = "Идентификатор роли")
        UUID id,

        @NotNull
        UserShortDto user
) {}
