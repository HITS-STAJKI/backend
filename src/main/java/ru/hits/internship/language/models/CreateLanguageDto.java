package ru.hits.internship.language.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "dto для создания языков")
public class CreateLanguageDto {
    @Schema(description = "Название языка", example = "Java")
    @NotNull(message = "Название языка должно быть заполнено")
    @NotBlank(message = "Название языка должно быть заполнено")
    private String name;
}
