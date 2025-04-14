package ru.hits.internship.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.user.*;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.CuratorEntity;
import ru.hits.internship.user.model.entity.role.DeanEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.model.entity.role.TeacherEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity user);
    UserShortDto toShortDto(UserEntity user);
    AuthUser toAuthUser(UserEntity user);
    UserEntity toEntity(RegistrationRequestDto registrationDto);
    UserEntity updateUser(@MappingTarget UserEntity user, UserEditDto editDto);

    @Mapping(target = "id", source = "user.id")
    UserDetailsDto toDetailsDto(
            UserEntity user,
            DeanEntity dean,
            TeacherEntity teacher,
            CuratorEntity curator,
            StudentEntity student
    );

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "roles", source = "user.roles")
    UserDto toDtoFromStudent(StudentEntity studentEntity);
}
