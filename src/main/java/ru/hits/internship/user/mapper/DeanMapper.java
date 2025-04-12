package ru.hits.internship.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.user.model.dto.role.response.DeanDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.DeanEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = UserMapper.class)
public interface DeanMapper {
    DeanMapper INSTANCE = Mappers.getMapper(DeanMapper.class);

    DeanDto toDto(DeanEntity dean);

    @Mapping(target = "id", ignore = true)
    DeanEntity toEntity(UserEntity user);
}