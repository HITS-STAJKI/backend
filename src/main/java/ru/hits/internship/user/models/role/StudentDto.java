package ru.hits.internship.user.models.role;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record StudentDto(
        @Schema(description = "Идентификатор пользователя")
        UUID id,

        @Schema(description = "Идентификатор студента")
        UUID studentId,

        @Schema(description = "Идентификатор группы")
        UUID groupId,

        @Schema(description = "Адрес электронной почты", example = "example@example.ru")
        String email,

        @Schema(description = "Имя пользователя", example = "Иван")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Иванов")
        String lastName,

        @Schema(description = "Номер группы", example = "972202")
        String groupNumber
) {}
