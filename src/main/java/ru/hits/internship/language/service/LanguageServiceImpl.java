package ru.hits.internship.language.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.language.entity.LanguageEntity;
import ru.hits.internship.language.mapper.LanguageMapper;
import ru.hits.internship.language.models.CreateLanguageDto;
import ru.hits.internship.language.models.LanguageDto;
import ru.hits.internship.language.models.UpdateLanguageDto;
import ru.hits.internship.language.repository.LanguageRepository;
import ru.hits.internship.language.specification.LanguageSpecification;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository repository;
    private final LanguageMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<LanguageDto> getLanguages(String query) {
        Specification<LanguageEntity> spec = LanguageSpecification.hasName(query);
        var languages = repository.findAll(spec);

        return languages
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LanguageDto createLanguage(CreateLanguageDto createLanguageDto) {
        var languageEntity = mapper.toEntity(createLanguageDto);

        LanguageEntity entity = repository.save(languageEntity);

        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public LanguageDto updateLanguage(UUID languageId, UpdateLanguageDto updateLanguageDto) {
        var languageEntity = repository.findById(languageId)
                .orElseThrow(() -> new NotFoundException(String.format("Язык с id: %s не найден", languageId)));

        mapper.updateEntity(languageEntity, updateLanguageDto);
        LanguageEntity entity = repository.save(languageEntity);

        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public Response deleteLanguage(UUID languageId) {
        var languageEntity = repository.findById(languageId)
                .orElseThrow(() -> new NotFoundException(String.format("Язык с id: %s не найден", languageId)));

        repository.delete(languageEntity);

        return new Response(
                String.format("Язык с id: %s успешно удален", languageEntity.getId()),
                HttpStatus.OK.value()
        );
    }
}
