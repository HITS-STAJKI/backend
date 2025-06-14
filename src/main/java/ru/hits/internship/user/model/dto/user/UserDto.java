package ru.hits.internship.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.hits.internship.user.model.dto.role.response.RoleDto;

import java.util.Set;
import java.util.UUID;

@Schema(description = "Модель пользователя")
public record UserDto (
        @Schema(description = "Идентификатор пользователя")
        UUID id,

        @Schema(description = "Адрес электронной почты", example = "example@example.ru")
        String email,

        @Schema(description = "ФИО пользователя", example = "Иванов Иван Иванович")
        String fullName,

        @ArraySchema(schema = @Schema(implementation = RoleDto.class, description = "Роль пользователя"))
        Set<RoleDto> roles
) {}