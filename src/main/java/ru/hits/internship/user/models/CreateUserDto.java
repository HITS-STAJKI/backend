package ru.hits.internship.user.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "dto для создания пользователей")
public class CreateUserDto {
    @Schema(description = "Имя пользователя", example = "Иван")
    @NotNull(message = "Имя должно быть заполнено")
    @NotBlank(message = "Имя должно быть заполнено")
    private String firstName;
    @Schema(description = "Фамилия пользователя", example = "Иванов")
    @NotNull(message = "Фамилия должна быть заполнена")
    @NotBlank(message = "Фамилия должна быть заполнена")
    private String lastName;
    @NotNull
    @Size(min = 1, message = "Минимальная длина не менее 1 символа")
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+", message = "Неверный адрес электронной почты")
    @Schema(description = "Адрес электронной почты", example = "example@example.ru")
    private String email;
}
