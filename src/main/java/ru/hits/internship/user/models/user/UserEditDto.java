package ru.hits.internship.user.models.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Модель редактирования пользователя")
public record UserEditDto(
        @Schema(description = "Имя пользователя", example = "Иван")
        @NotNull(message = "Имя должно быть заполнено")
        @NotBlank(message = "Имя должно быть заполнено")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Иванов")
        @NotNull(message = "Фамилия должна быть заполнена")
        @NotBlank(message = "Фамилия должна быть заполнена")
        String lastName
) {}
