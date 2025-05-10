package ru.hits.internship.chat.model.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatInfoDto {
    @Schema(description = "Идентификатор чата")
    private UUID chatId;

    @Schema(description = "Идентификатор студента")
    private UUID studentId;

    @Schema(description = "Число непрочитанных сообщений для текущего пользователя")
    private long unreadCount;
}
