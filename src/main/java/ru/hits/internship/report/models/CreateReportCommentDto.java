package ru.hits.internship.report.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto для создания комментария к отчету")
public class CreateReportCommentDto {
    @Schema(description = "Текст комментария", example = "Отредактировал отчет")
    private String content;
}

