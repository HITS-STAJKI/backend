package ru.hits.internship.file.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.internship.file.enumeration.FileType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    @Schema(description = "Идентификатор файла", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    private String id;
    @Schema(description = "Имя файла", example = "report.pdf")
    private String name;
    @Schema(description = "Тип файла", example = "application/pdf")
    private String contentType;
    @Schema(description = "Размер файла в байтах", example = "1024")
    private Long size;
    @Schema(description = "Время создания файла (загрузки на сервер)")
    private LocalDateTime createdAt;
    @Schema(description = "Тип файла", example = "LOGO")
    private FileType type;
}
