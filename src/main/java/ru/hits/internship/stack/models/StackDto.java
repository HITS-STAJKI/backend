package ru.hits.internship.stack.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto стека")
public class StackDto {
    @Schema(description = "Идентификатор стека", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор должен быть заполнен")
    @NotBlank(message = "Идентификатор должен быть заполнен")
    private String id;
    @Schema(description = "Название стека", example = "Java")
    @NotNull(message = "Название стека должно быть заполнено")
    @NotBlank(message = "Название стека должно быть заполнено")
    private String name;
}
