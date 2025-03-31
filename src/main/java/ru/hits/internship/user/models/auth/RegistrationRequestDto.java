package ru.hits.internship.user.models.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Schema(description = "Модель регистрации")
public record RegistrationRequestDto(
        @Schema(description = "Адрес электронной почты", example = "example@example.ru")
        @NotNull
        @Pattern(
                regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+",
                message = "Неверный адрес электронной почты"
        )
        String email,

        @Schema(description = "Пароль пользователя", example = "password123")
        @NotNull
        @Size(min = 6, max = 100, message = "Минимальная длина пароля - 6, максимальная - 100")
        String password,

        @Schema(description = "Имя пользователя", example = "Иван")
        @NotNull(message = "Имя должно быть заполнено")
        @NotBlank(message = "Имя должно быть заполнено")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Иванов")
        @NotNull(message = "Фамилия должна быть заполнена")
        @NotBlank(message = "Фамилия должна быть заполнена")
        String lastName
) {}