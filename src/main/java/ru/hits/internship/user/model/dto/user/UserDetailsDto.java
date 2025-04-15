package ru.hits.internship.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import ru.hits.internship.user.model.dto.role.response.CuratorShortDto;
import ru.hits.internship.user.model.dto.role.response.DeanShortDto;
import ru.hits.internship.user.model.dto.role.response.StudentShortDto;
import ru.hits.internship.user.model.dto.role.response.TeacherShortDto;

import java.util.UUID;

@Schema(description = "Подробная модель пользователя")
public record UserDetailsDto (
        @NotNull
        @Schema(description = "Идентификатор пользователя")
        UUID id,

        @NotNull
        @Schema(description = "Адрес электронной почты", example = "example@example.ru")
        String email,

        @NotNull
        @Schema(description = "ФИО пользователя", example = "Иванов Иван Иванович")
        String fullName,

        @Nullable
        DeanShortDto dean,

        @Nullable
        StudentShortDto student,

        @Nullable
        CuratorShortDto curator,

        @Nullable
        TeacherShortDto teacher
) {}