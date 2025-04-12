package ru.hits.internship.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.hits.internship.user.model.dto.role.response.*;

import java.util.Set;
import java.util.UUID;

@Schema(description = "Модель пользователя")
public record UserDto (
        @Schema(description = "Идентификатор пользователя")
        UUID id,

        @Schema(description = "Адрес электронной почты", example = "example@example.ru")
        String email,

        @Schema(description = "Имя пользователя", example = "Иван")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Иванов")
        String lastName,

        @ArraySchema(schema = @Schema(implementation = RoleDto.class, description = "Роль пользователя"))
        Set<RoleDto> roles
) {}