package ru.hits.internship.report.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto для обновления комментария к отчету")
public class UpdateReportCommentDto {
    @Schema(description = "Текст комментария", example = "Отличный отчет")
    private String content;
}
