package ru.hits.internship.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.user.model.dto.role.response.StudentDto;
import ru.hits.internship.user.model.entity.role.StudentEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = UserMapper.class)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto toDto(StudentEntity dean);

    @Mapping(target = "id", ignore = true)
    StudentEntity toEntity(GroupEntity group);
}