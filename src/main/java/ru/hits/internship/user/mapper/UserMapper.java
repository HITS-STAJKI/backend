package ru.hits.internship.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.group.dto.GroupShortDto;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.role.response.RoleDto;
import ru.hits.internship.user.model.dto.role.response.StudentShortDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.dto.user.UserDetailsDto;
import ru.hits.internship.user.model.dto.user.UserDto;
import ru.hits.internship.user.model.dto.user.UserEditDto;
import ru.hits.internship.user.model.dto.user.UserEmailEditDto;
import ru.hits.internship.user.model.dto.user.UserShortDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.CuratorEntity;
import ru.hits.internship.user.model.entity.role.DeanEntity;
import ru.hits.internship.user.model.entity.role.EducationalProgramLeadEntity;
import ru.hits.internship.user.model.entity.role.RoleEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.model.entity.role.TeacherEntity;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = StudentMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity user);

    UserShortDto toShortDto(UserEntity user);

    AuthUser toAuthUser(UserEntity user);

    UserEntity toEntity(RegistrationRequestDto registrationDto);

    UserEntity updateUser(@MappingTarget UserEntity user, UserEditDto editDto);

    UserEntity updateUserEmail(@MappingTarget UserEntity user, UserEmailEditDto editDto);

    @Mapping(target = "id", source = "user.id")
    UserDetailsDto toDetailsDto(
            UserEntity user,
            DeanEntity dean,
            TeacherEntity teacher,
            CuratorEntity curator,
            StudentEntity student,
            EducationalProgramLeadEntity educationalProgramLead
    );

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "roles", source = "user.roles")
    UserDto toDtoFromStudent(StudentEntity studentEntity);

    @Mapping(target = "id", source = "student.id")
    @Mapping(target = "isAcadem", source = "student.isAcadem")
    @Mapping(target = "isGraduated", source = "student.isGraduated")
    @Mapping(target = "chatId", source = "student.chat.id")
    @Mapping(target = "group", source = "student.group")
    StudentShortDto toStudentShortDto(StudentEntity student);

    @Mapping(target = "id", source = "group.id")
    @Mapping(target = "number", source = "group.number")
    GroupShortDto toGroupShortDto(GroupEntity group);


    default Map<UserRole, RoleDto> mapRoles(Set<RoleEntity> roles) {
        return roles
                .stream()
                .collect(
                        Collectors.toMap(
                                RoleEntity::getUserRole,
                                roleEntity -> new RoleDto(roleEntity.getId(), roleEntity.getUserRole())
                        )
                );
    }
}
