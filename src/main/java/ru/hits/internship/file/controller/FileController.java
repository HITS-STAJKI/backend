package ru.hits.internship.file.controller;

// pdf, docx


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.file.dto.FileDto;
import ru.hits.internship.file.service.FileService;
import ru.hits.internship.file.util.FileUtils;
import ru.hits.internship.file.validation.FileSize;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

@RestController
@Tag(name = "Files", description = "Отвечает за работу с файлами")
@RequestMapping(value = "/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл",
            description = "Позволяет загрузить файл")
    @SecurityRequirement(name = "bearerAuth")
    //@PreAuthorize("hasRole('STUDENT')") TODO: пока закомментировал для тестирования
    public FileDto uploadFile(@AuthenticationPrincipal AuthUser authUser,
                              @RequestParam("file") @NotNull @FileSize MultipartFile file) {
        return fileService.uploadFile(authUser.id(), file);
    }

    @GetMapping("/{id}/download")
    @Operation(summary = "Скачать файл",
            description = "Позволяет скачать файл")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("@acf.hasAccess(#authUser.id(), #id) or hasAnyRole('DEAN', 'CURATOR')")
    public ResponseEntity<Resource> downloadFile(@AuthenticationPrincipal AuthUser authUser,
                                                 @PathVariable @Parameter(description = "id файла") UUID id) {
        Resource resource = fileService.downloadFile(authUser.id(), id);
        FileDto fileDto = fileService.getFileMetadata(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, FileUtils.getContentDisposition(fileDto.getName()))
                .contentType(MediaType.parseMediaType(fileDto.getContentType()))
                .body(resource);
    }

    @GetMapping("/{id}/metadata")
    @Operation(summary = "Получить метаданные файла",
            description = "Позволяет получить метаданные файла")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("@acf.hasAccess(#authUser.id(), #id) or hasAnyRole('DEAN', 'CURATOR')")
    public FileDto getFileMetadata(@AuthenticationPrincipal AuthUser authUser,
                                   @PathVariable @Parameter(description = "id файла") UUID id) {
        return fileService.getFileMetadata(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить файл",
            description = "Позволяет удалить файл")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("@acf.hasAccess(#authUser.id(), #id) or hasAnyRole('DEAN', 'CURATOR')")
    public Response deleteFile(@AuthenticationPrincipal AuthUser authUser,
                               @PathVariable @Parameter(description = "id файла") UUID id) {
        fileService.deleteFile(id);
        return new Response("Файл был успешно удален", HttpStatus.OK.value());
    }

    @GetMapping("/my")
    @Operation(summary = "Получить свои файлы",
            description = "Позволяет получить свои файлы")
    @SecurityRequirement(name = "bearerAuth")
    public PagedListDto<FileDto> getMyFiles(@AuthenticationPrincipal AuthUser authUser,
                                            @ParameterObject @PageableDefault(sort = "name",
                                                    direction = Sort.Direction.ASC) Pageable pageable) {
        return fileService.getMyFiles(authUser, pageable);
    }

}
