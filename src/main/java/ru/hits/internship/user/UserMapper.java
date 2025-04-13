package ru.hits.internship.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.dto.user.UserDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity entity);
    AuthUser toAuthUser(UserEntity entity);
    UserEntity toEntity(RegistrationRequestDto registrationDto);

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "userId", source = "user.id")
    StudentDto toStudentDto(StudentEntity studentEntity);
}
