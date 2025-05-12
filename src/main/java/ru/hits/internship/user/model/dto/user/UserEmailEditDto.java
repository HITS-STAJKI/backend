package ru.hits.internship.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Модель редактирования электронной почты пользователя")
public record UserEmailEditDto(
        @Schema(description = "Адрес электронной почты", example = "example@example.ru")
        @NotNull
        @Pattern(
                regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+",
                message = "Неверный адрес электронной почты"
        )
        String email
) {}
