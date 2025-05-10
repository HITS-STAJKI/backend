package ru.hits.internship.chat.model.message;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageRequest {
    @NotBlank(message = "Нельзя отправить пустое сообщение")
    @Length(max = 4096, message = "Длина сообщения не должна превышать 4096 символов")
    private String content;
}
