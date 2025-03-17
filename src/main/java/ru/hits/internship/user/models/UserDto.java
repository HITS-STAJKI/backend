package ru.hits.internship.user.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(description = "dto пользователя")
public class UserDto {
    @Schema(description = "Идентификатор пользователя", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор должен быть заполнен")
    @NotBlank(message = "Идентификатор должен быть заполнен")
    private UUID id;
    @Schema(description = "Имя пользователя", example = "Иван")
    @NotNull(message = "Имя должно быть заполнено")
    @NotBlank(message = "Имя должно быть заполнено")
    private String firstName;
    @Schema(description = "Фамилия пользователя", example = "Иванов")
    @NotNull(message = "Фамилия должна быть заполнена")
    @NotBlank(message = "Фамилия должна быть заполнена")
    private String lastName;
    @NotNull(message = "Электронный адрес должен быть заполнен")
    @NotBlank(message = "Электронный адрес должен быть заполнен")
    @Schema(description = "Адрес электронной почты", example = "example@example.ru")
    private String email;
}
