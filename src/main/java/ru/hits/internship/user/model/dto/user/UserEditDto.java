package ru.hits.internship.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Модель редактирования пользователя")
public record UserEditDto(
        @Schema(description = "Полное имя пользователя", example = "Иванов Иван Иванович")
        @NotBlank(message = "Полное имя должно быть заполнено")
        String fullName
) {}
