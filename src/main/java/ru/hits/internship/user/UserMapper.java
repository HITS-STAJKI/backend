package ru.hits.internship.user;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.user.entity.UserEntity;
import ru.hits.internship.user.models.auth.RegistrationRequestDto;
import ru.hits.internship.user.models.user.UserDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity entity);
    UserEntity toEntity(RegistrationRequestDto registrationDto);
}
