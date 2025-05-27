package ru.hits.internship.chat.model.message;

import java.util.Set;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageToStudentsRequest {
    @Schema(description = "Идентификаторы студентов", example = "[\"3ea42ea8-5258-4086-a43f-113ff89577a1\"]")
    @NotNull(message = "Идентификаторы студентов должны быть заполнены")
    @NotEmpty(message = "Идентификаторы студентов должны быть заполнены")
    private Set<UUID> studentIds;
    @NotBlank(message = "Нельзя отправить пустое сообщение")
    @Length(max = 4096, message = "Длина сообщения не должна превышать 4096 символов")
    private String content;
}
