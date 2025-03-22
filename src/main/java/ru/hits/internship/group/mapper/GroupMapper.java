package ru.hits.internship.group.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.hits.internship.group.dto.CreateGroupDto;
import ru.hits.internship.group.dto.GroupDto;
import ru.hits.internship.group.dto.UpdateGroupDto;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.user.entity.role.StudentEntity;
import ru.hits.internship.user.models.role.response.StudentResponseDto;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "id", ignore = true)
    GroupEntity toEntity(CreateGroupDto createGroupDto);

    // TODO: Согласовать вложенный маппинг для студентов -> интервью
//    @Mapping(target = "studentsCount", source = "students", qualifiedByName = "studentsCount")
//    GroupDto toDto(GroupEntity groupEntity);


    @Mapping(target = "students", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateGroupEntity(@MappingTarget GroupEntity groupEntity, UpdateGroupDto updateGroupDto);


    @Named("studentsCount")
    default int studentsCount(Set<StudentEntity> students) {
        return Optional.ofNullable(students).map(Set::size).orElse(0);
    }
}
