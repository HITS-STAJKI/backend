package ru.hits.internship.language.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Язык программирования", description = "Отвечает за работу с языками для отборов")
@RequestMapping(value = "/api/v1/language")
public class LanguageController {
    @Operation(
            summary = "Получить список языков",
            description = "Позволяет получить список доступных языков"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<LanguageDto> getLanguageList(
            @RequestParam(name = "query") @Parameter(description = "Название языка") String query
    ) {
        return null;
    }

    @Operation(summary = "Создать язык программирования")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping()
    public LanguageDto createLanguage(
            @RequestBody CreateLanguageDto createUpdateStackDto
    ) {
        return null;
    }

    @Operation(summary = "Обновить язык программирования")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{languageId}")
    public LanguageDto updateLanguage(
            @PathVariable @Parameter(description = "Идентификатор языка", required = true) UUID languageId,
            @RequestBody UpdateLanguageDto createUpdateStackDto
    ) {
        return null;
    }

    @Operation(summary = "Удалить язык программирования")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{languageId}")
    public Response deleteLanguage(
            @PathVariable @Parameter(description = "Идентификатор языка", required = true) UUID languageId
    ) {
        return null;
    }
}
