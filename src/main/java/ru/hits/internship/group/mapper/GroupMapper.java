package ru.hits.internship.group.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.hits.internship.group.dto.CreateGroupDto;
import ru.hits.internship.group.dto.GroupDto;
import ru.hits.internship.group.dto.UpdateGroupDto;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;

import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "id", ignore = true)
    GroupEntity toEntity(CreateGroupDto createGroupDto);

    // TODO: Согласовать вложенный маппинг для студентов -> интервью, пока игнорим
    //@Mapping(target = "studentsCount", source = "students", qualifiedByName = "studentsCount")
    @Mapping(target = "studentsCount", ignore = true)
    @Mapping(target = "students", ignore = true)
    GroupDto toDto(GroupEntity groupEntity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateGroupEntity(@MappingTarget GroupEntity groupEntity, UpdateGroupDto updateGroupDto);


    @Named("studentsCount")
    default int studentsCount(Set<StudentEntity> students) {
        return Optional.ofNullable(students).map(Set::size).orElse(0);
    }
}
