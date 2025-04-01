package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.hits.internship.user.model.common.IRole;
import ru.hits.internship.user.model.common.UserRole;

import java.util.UUID;

@Schema(description = "Модель роли")
public record RoleDto(
        @Schema(description = "Идентификатор роли")
        UUID id,

        @Schema(description = "Роль")
        UserRole userRole
) implements IRole {

        @Override
        public UserRole getUserRole() {
                return userRole;
        }
}
