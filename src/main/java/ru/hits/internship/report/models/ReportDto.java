package ru.hits.internship.report.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    @Schema(description = "Идентификатор отчета", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор должен быть заполнен")
    @NotBlank(message = "Идентификатор должен быть заполнен")
    private UUID id;
    @Schema(description = "Время отправки комментария")
    private LocalDateTime createdAt;
    @Schema(description = "Время обновления комментария")
    private LocalDateTime modifiedAt;
    @Schema(description = "Идентификатор файла с отчетом")
    private UUID fileId;
    @Schema(description = "Оценка отчета", example = "5")
    private Integer grade;
}
