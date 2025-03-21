package ru.hits.internship.user.models.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.hits.internship.user.annotations.EmailSchema;

@Schema(description = "Модель login'а")
public record LoginCredentialsDto(
        @EmailSchema
        String email,

        @NotNull
        @Size(min = 6, max = 100, message = "Минимальная длина пароля - 6, максимальная - 100")
        @Schema(description = "Пароль пользователя", example = "password123")
        String password
) {}
