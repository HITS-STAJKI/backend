package ru.hits.internship.user.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.model.dto.user.UserDetailsDto;
import ru.hits.internship.user.model.dto.user.UserDto;
import ru.hits.internship.user.model.dto.user.UserEditDto;
import ru.hits.internship.user.model.dto.user.UserFilter;
import ru.hits.internship.user.model.dto.user.UserShortDto;

import java.util.UUID;

public interface UserService {
    Response deleteRole(UUID userId, UUID roleId);
    UserShortDto updateUser(UUID userId, UserEditDto editDto);
    UserDetailsDto getUserDetailsById(UUID id);
    PagedListDto<UserDto> getAllUsers(UUID userId, UserFilter userFilter, Pageable pageable);
}
