package ru.hits.internship.interview.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto для создания комментария к отбору")
public class CreateUpdateInterviewCommentDto {
    @Schema(description = "Текст комментария", example = "Прошел первый этап")
    @NotNull(message = "Текст комментария должен быть заполнен")
    @NotBlank(message = "Текст комментария должен быть заполнен")
    private String content;
}
