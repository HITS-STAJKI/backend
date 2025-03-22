package ru.hits.internship.interview.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "dto для обновления комментария к отбору")
public class UpdateInterviewCommentDto {
    @Schema(description = "Текст комментария", example = "Прошел первый этап")
    private String content;
}
