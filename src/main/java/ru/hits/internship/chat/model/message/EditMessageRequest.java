package ru.hits.internship.chat.model.message;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditMessageRequest {
    @NotBlank(message = "Сообщение не должно быть пустым")
    @Length(max = 4096, message = "Сообщение не должно превышать 4096 символов")
    private String content;
}
