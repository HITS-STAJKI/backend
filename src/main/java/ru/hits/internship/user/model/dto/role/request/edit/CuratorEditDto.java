package ru.hits.internship.user.model.dto.role.request.edit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Модель для изменения куратора")
public record CuratorEditDto(
        @Schema(description = "Идентификатор компании")
        @NotNull
        UUID companyId
) {}