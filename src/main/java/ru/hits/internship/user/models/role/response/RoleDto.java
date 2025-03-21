package ru.hits.internship.user.models.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.hits.internship.user.models.role.UserRole;

import java.util.UUID;

@Schema(description = "Модель роли")
public record RoleDto(
        @Schema(description = "Идентификатор роли")
        UUID id,

        @Schema(description = "Роль")
        UserRole role
) {}
