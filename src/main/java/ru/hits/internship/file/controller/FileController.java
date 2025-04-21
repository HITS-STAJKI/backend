package ru.hits.internship.file.controller;

// pdf, docx


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.file.dto.FileDto;
import java.util.UUID;

@RestController
@Tag(name = "Файлы отчетов", description = "Отвечает за работу с файлами отчетов")
@RequestMapping(value = "/api/v1/files")
public class FileController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл отчета",
            description = "Позволяет загрузить файл отчета")
    public FileDto uploadFile(@RequestParam("file") @NotNull MultipartFile file) {
        return null;
    }

    @GetMapping("/{id}/download")
    @Operation(summary = "Скачать файл отчета",
            description = "Позволяет скачать файл отчета")
    public Resource downloadFile(@PathVariable UUID id) {
        return null;
    }

    @GetMapping("/{id}/metadata")
    @Operation(summary = "Получить метаданные файла отчета",
            description = "Позволяет получить метаданные файла отчета")
    public FileDto getFileMetadata(@PathVariable UUID id) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить файл отчета",
            description = "Позволяет удалить файл отчета")
    public Response deleteFile(@PathVariable UUID id) {
        return null;
    }

}
