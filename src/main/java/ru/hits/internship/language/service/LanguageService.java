package ru.hits.internship.language.service;

import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.language.models.CreateLanguageDto;
import ru.hits.internship.language.models.LanguageDto;
import ru.hits.internship.language.models.UpdateLanguageDto;

import java.util.List;
import java.util.UUID;

public interface LanguageService {
    List<LanguageDto> getLanguages(String query);

    LanguageDto createLanguage(CreateLanguageDto createLanguageDto);

    LanguageDto updateLanguage(UUID languageId, UpdateLanguageDto updateLanguageDto);

    Response deleteLanguage(UUID languageId);
}
