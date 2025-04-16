package ru.hits.internship.practice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto для обновления практики")
public class UpdatePracticeDto {
    @Schema(description = "Статус оплачиваемости", example = "true")
    @NotNull(message = "Статус должен быть заполнен")
    private Boolean isPaid;
}
