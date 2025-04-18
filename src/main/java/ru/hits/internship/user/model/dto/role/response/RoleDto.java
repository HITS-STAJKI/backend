package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.hits.internship.user.model.common.UserRole;

import java.util.UUID;

@Schema(description = "Модель роли")
public record RoleDto(
        @NotNull
        @Schema(description = "Идентификатор роли")
        UUID id,

        @Schema(description = "Роль")
        UserRole userRole
) {}