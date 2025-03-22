package ru.hits.internship.practice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@Schema(description = "dto для практик")
public class PracticeDto {
    @Schema(description = "Идентификатор практики", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор должен быть заполнен")
    @NotBlank(message = "Идентификатор должен быть заполнен")
    private UUID id;
    @Schema(description = "Время создания практики")
    private LocalDateTime createdAt;
    @Schema(description = "Статус оплачиваемости", example = "true")
    private Boolean isPaid;
    @Schema(description = "Является ли заархивированной", example = "true")
    private Boolean isArchived;
    @Schema(description = "Подтверждена куратором", example = "true")
    private Boolean isApproved;
}
