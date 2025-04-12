package ru.hits.internship.user.model.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Модель для изменения пароля")
public record PasswordEditDto(
        @Schema(description = "Пароль пользователя", example = "password123")
        @NotNull
        @Size(min = 6, max = 100, message = "Минимальная длина пароля - 6, максимальная - 100")
        String oldPassword,

        @Schema(description = "Пароль пользователя", example = "password123")
        @NotNull
        @Size(min = 6, max = 100, message = "Минимальная длина пароля - 6, максимальная - 100")
        String newPassword,

        @Schema(description = "Пароль пользователя", example = "password123")
        @NotNull
        @Size(min = 6, max = 100, message = "Минимальная длина пароля - 6, максимальная - 100")
        String repeatNewPassword
) {}
