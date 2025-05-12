package ru.hits.internship.user.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.model.dto.user.*;

import java.util.UUID;

public interface UserService {
    Response deleteRole(UUID userId, UUID roleId);
    UserShortDto updateUser(UUID userId, UserEditDto editDto);
    UserShortDto updateUserEmail(UUID userId, UserEmailEditDto editDto);
    UserDetailsDto getUserDetailsById(UUID id);
    PagedListDto<UserDto> getAllUsers(UUID userId, UserFilter userFilter, Pageable pageable);
}
