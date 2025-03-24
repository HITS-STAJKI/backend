package ru.hits.internship.practice.mapper;

import org.mapstruct.Mapper;
import ru.hits.internship.practice.entity.PracticeEntity;
import ru.hits.internship.practice.models.PracticeDto;

@Mapper(componentModel = "spring")
public interface PracticeMapper {
    PracticeDto toDto(PracticeEntity entity);
}
