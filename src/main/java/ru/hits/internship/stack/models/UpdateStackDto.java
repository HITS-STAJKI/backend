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
@Schema(description = "dto для обновления стека")
public class UpdateStackDto {
    @Schema(description = "Название стека", example = "Backend")
    @NotNull(message = "Название стека должно быть заполнено")
    @NotBlank(message = "Название стека должно быть заполнено")
    private String name;
}
