package ru.hits.internship.practice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(description = "dto для создания практики")
public class CreatePracticeDto {
    @Schema(description = "Идентификатор компании, в которую подается практика", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор должен быть заполнен")
    @NotBlank(message = "Идентификатор должен быть заполнен")
    private UUID companyId;
    @Schema(description = "Статус оплачиваемости", example = "true")
    @NotNull(message = "Статус должен быть заполнен")
    @NotBlank(message = "Статус должен быть заполнен")
    private Boolean isPaid;
}
