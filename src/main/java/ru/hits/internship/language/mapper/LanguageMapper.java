package ru.hits.internship.language.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.hits.internship.language.entity.LanguageEntity;
import ru.hits.internship.language.models.CreateLanguageDto;
import ru.hits.internship.language.models.LanguageDto;
import ru.hits.internship.language.models.UpdateLanguageDto;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    @Mapping(target = "id", ignore = true)
    LanguageEntity toEntity(CreateLanguageDto createLanguageDto);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
    void updateEntity(@MappingTarget LanguageEntity languageEntity, UpdateLanguageDto updateLanguageDto);

    LanguageDto toDto(LanguageEntity entity);
}
