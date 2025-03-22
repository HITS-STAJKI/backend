package ru.hits.internship.interview.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hits.internship.user.models.user.UserDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(description = "dto комментария к отбору")
public class InterviewCommentDto {
    @Schema(description = "Идентификатор комментария", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор должен быть заполнен")
    @NotBlank(message = "Идентификатор должен быть заполнен")
    private UUID id;
    @Schema(description = "Содержание комментария", example = "Комментарий")
    @NotNull(message = "Содержание должно быть заполнено")
    @NotBlank(message = "Содержание должно быть заполнено")
    private String content;
    @Schema(description = "Время отправки комментария")
    private LocalDateTime createdAt;
    @Schema(description = "Время обновления комментария")
    private LocalDateTime modifiedAt;
    @NotNull
    private UserDto author;
}
