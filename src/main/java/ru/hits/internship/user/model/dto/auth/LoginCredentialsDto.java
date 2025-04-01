package ru.hits.internship.user.model.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Модель login'а")
public record LoginCredentialsDto(
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
        String password
) {}
