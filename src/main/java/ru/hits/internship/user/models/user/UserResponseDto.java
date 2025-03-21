package ru.hits.internship.user.models.user;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import ru.hits.internship.user.models.role.response.RoleResponseDto;

import java.util.Set;
import java.util.UUID;

@Schema(description = "Модель пользователя")
public record UserResponseDto(
        @Schema(description = "Идентификатор пользователя")
        UUID id,

        @Schema(description = "Адрес электронной почты", example = "example@example.ru")
        String email,

        @Schema(description = "Имя пользователя", example = "Иван")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Иванов")
        String lastName,

        @ArraySchema(schema = @Schema(implementation = RoleResponseDto.class, description = "Роль пользователя"))
        Set<RoleResponseDto> roles
) {}