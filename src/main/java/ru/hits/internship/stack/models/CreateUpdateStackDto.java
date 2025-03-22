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
@Schema(description = "dto для создания стека")
public class CreateUpdateStackDto {
    @Schema(description = "Название стека", example = "Java")
    @NotNull(message = "Название стека должно быть заполнено")
    @NotBlank(message = "Название стека должно быть заполнено")
    private String name;
}
