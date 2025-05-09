package ru.hits.internship.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.user.model.dto.role.response.StudentDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = UserMapper.class)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "chatId", source = "chat.id")
    StudentDto toDto(StudentEntity student);

    @Mapping(target = "id", ignore = true)
    StudentEntity toEntity(UserEntity user, GroupEntity group);

    @Mapping(target = "id", ignore = true)
    StudentEntity updateStudent(@MappingTarget StudentEntity student, GroupEntity group);

    default StudentDto toDtoWithUnreadCount(StudentEntity student, Long unreadCount) {
        StudentDto studentDto = toDto(student);
        studentDto = new StudentDto(
                studentDto.id(),
                studentDto.isAcadem(),
                studentDto.isGraduated(),
                studentDto.user(),
                studentDto.group(),
                studentDto.chatId(),
                unreadCount
        );
        return studentDto;
    }
}