package ru.hits.internship.user.models.user;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.hits.internship.user.annotations.EmailSchema;

import java.util.UUID;

@Schema(description = "Модель пользователя")
public record UserResponseDto(
        @Schema(description = "Идентификатор пользователя", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
        UUID id,

        @EmailSchema
        String email,

        @Schema(description = "Имя пользователя", example = "Иван")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Иванов")
        String lastName
) {}