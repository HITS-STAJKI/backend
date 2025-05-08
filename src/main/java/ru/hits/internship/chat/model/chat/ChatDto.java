package ru.hits.internship.chat.model.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    @Schema(description = "Идентификатор чата")
    private UUID id;
    @Schema(description = "Идентификатор студента, участвующего в чате")
    private UUID studentId;
}
