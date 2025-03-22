package ru.hits.internship.practice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "dto для обновления практики")
public class UpdatePracticeDto {
    @Schema(description = "Статус оплачиваемости", example = "true")
    @NotNull(message = "Статус должен быть заполнен")
    @NotBlank(message = "Статус должен быть заполнен")
    private Boolean isPaid;
}
