package ru.hits.internship.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.dto.user.UserDetailsDto;
import ru.hits.internship.user.model.dto.user.UserDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.CuratorEntity;
import ru.hits.internship.user.model.entity.role.DeanEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.model.entity.role.TeacherEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity entity);
    AuthUser toAuthUser(UserEntity entity);
    UserEntity toEntity(RegistrationRequestDto registrationDto);

    @Mapping(target = "id", source = "user.id")
    UserDetailsDto toDetailsDto(
            UserEntity user,
            DeanEntity dean,
            TeacherEntity teacher,
            CuratorEntity curator,
            StudentEntity student
    );
}
