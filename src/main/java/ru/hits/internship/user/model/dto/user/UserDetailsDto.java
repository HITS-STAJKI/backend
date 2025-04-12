package ru.hits.internship.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.hits.internship.user.model.dto.role.response.*;

import java.util.Optional;
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
        @Schema(description = "Имя пользователя", example = "Иван")
        String firstName,

        @NotNull
        @Schema(description = "Фамилия пользователя", example = "Иванов")
        String lastName,

        Optional<DeanShortDto> dean,

        Optional<StudentShortDto> student,

        Optional<CuratorShortDto> curator,

        Optional<TeacherShortDto> teacher
) {}