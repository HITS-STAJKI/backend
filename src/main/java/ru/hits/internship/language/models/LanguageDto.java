package ru.hits.internship.language.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(description = "dto языка")
public class LanguageDto {
    @Schema(description = "Идентификатор языка", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор должен быть заполнен")
    @NotBlank(message = "Идентификатор должен быть заполнен")
    private UUID id;
    @Schema(description = "Название языка", example = "Java")
    @NotNull(message = "Название должно быть заполнено")
    @NotBlank(message = "Название должно быть заполнено")
    private String name;
}
