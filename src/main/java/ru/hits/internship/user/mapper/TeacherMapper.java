package ru.hits.internship.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.user.model.dto.role.response.TeacherDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.TeacherEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = UserMapper.class)
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherDto toDto(TeacherEntity teacher);

    @Mapping(target = "id", ignore = true)
    TeacherEntity toEntity(UserEntity user);
}