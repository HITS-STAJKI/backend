package ru.hits.internship.user;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.user.UserDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity entity);
    AuthUser toAuthUser(UserEntity entity);
    UserEntity toEntity(RegistrationRequestDto registrationDto);
}
