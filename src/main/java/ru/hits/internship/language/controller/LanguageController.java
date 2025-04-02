package ru.hits.internship.language.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.language.models.CreateLanguageDto;
import ru.hits.internship.language.models.LanguageDto;
import ru.hits.internship.language.models.UpdateLanguageDto;
import ru.hits.internship.language.service.LanguageService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Язык программирования", description = "Отвечает за работу с языками для отборов")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/language")
public class LanguageController {
    private final LanguageService languageService;

    @Operation(
            summary = "Получить список языков",
            description = "Позволяет получить список доступных языков"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<LanguageDto> getLanguageList(
            @RequestParam(name = "query", required = false) @Parameter(description = "Название языка") String query
    ) {
        return languageService.getLanguages(query);
    }

    @Operation(summary = "Создать язык программирования")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize("hasAnyRole('DEAN', 'CURATOR')")
    public LanguageDto createLanguage(
            @RequestBody @Valid CreateLanguageDto createLanguageDto
    ) {
        return languageService.createLanguage(createLanguageDto);
    }

    @Operation(summary = "Обновить язык программирования")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{languageId}")
    @PreAuthorize("hasAnyRole('DEAN', 'CURATOR')")
    public LanguageDto updateLanguage(
            @PathVariable @Parameter(description = "Идентификатор языка", required = true) UUID languageId,
            @RequestBody @Valid UpdateLanguageDto updateLanguageDto
    ) {
        return languageService.updateLanguage(languageId, updateLanguageDto);
    }

    @Operation(summary = "Удалить язык программирования")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{languageId}")
    @PreAuthorize("hasAnyRole('DEAN', 'CURATOR')")
    public Response deleteLanguage(
            @PathVariable @Parameter(description = "Идентификатор языка", required = true) UUID languageId
    ) {
        return languageService.deleteLanguage(languageId);
    }
}
