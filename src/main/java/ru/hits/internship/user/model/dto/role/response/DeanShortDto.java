package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Модель представителя деканата")
public record DeanShortDto(
        @NotNull
        @Schema(description = "Идентификатор роли")
        UUID id
) {}