package ru.hits.internship.common.models.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "dto для ответов")
public class Response {
    @Schema(description = "Содержание ответа", example = "Объект успешно удален")
    private String message;
    private LocalDateTime timestamp;
    @Schema(description = "Статус ответа", example = "200")
    private Integer status;

    public Response(String message, Integer status) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }
}
