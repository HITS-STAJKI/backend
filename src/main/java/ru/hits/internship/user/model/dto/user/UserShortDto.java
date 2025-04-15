package ru.hits.internship.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Модель пользователя")
public record UserShortDto (
        @NotNull
        @Schema(description = "Идентификатор пользователя")
        UUID id,

        @NotNull
        @Schema(description = "Адрес электронной почты", example = "example@example.ru")
        String email,


        @NotNull
        @Schema(description = "Полное имя пользователя", example = "Иванов Иван Иванович")
        String fullName
) {}