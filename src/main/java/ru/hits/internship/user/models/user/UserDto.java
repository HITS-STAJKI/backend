package ru.hits.internship.user.models.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.hits.internship.user.annotations.EmailSchema;

import java.util.UUID;

@Schema(description = "Модель пользователя")
public record UserDto(
        @Schema(description = "Идентификатор пользователя", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
        @NotNull(message = "Идентификатор должен быть заполнен")
        @NotBlank(message = "Идентификатор должен быть заполнен")
        UUID id,

        @EmailSchema
        String email,

        @Schema(description = "Имя пользователя", example = "Иван")
        @NotNull(message = "Имя должно быть заполнено")
        @NotBlank(message = "Имя должно быть заполнено")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Иванов")
        @NotNull(message = "Фамилия должна быть заполнена")
        @NotBlank(message = "Фамилия должна быть заполнена")
        String lastName
) {}
