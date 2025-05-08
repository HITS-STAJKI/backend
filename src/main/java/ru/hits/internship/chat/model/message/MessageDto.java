package ru.hits.internship.chat.model.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    @Schema(description = "Идентификатор сообщения")
    private UUID id;
    @Schema(description = "Контент сообщения")
    private String content;
    @Schema(description = "Идентификатор отправителя")
    private UUID senderId;
    @Schema(description = "Прочитано ли студентом (!)")
    private Boolean isRead;
    @Schema(description = "Изменено ли ?")
    private Boolean isEdited;
    @Schema(description = "Дата отправки")
    private LocalDateTime sentAt;
    @Schema(description = "Дата изменения")
    private LocalDateTime modifiedAt;
}
