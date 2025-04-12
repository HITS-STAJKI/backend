package ru.hits.internship.user.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.user.UserDetailsDto;
import ru.hits.internship.user.model.dto.user.UserDto;

import java.util.UUID;

public interface UserService {
    UserDetailsDto getUserDetailsById(UUID id);
    PagedListDto<UserDto> getAllUsers(UUID userId, Pageable pageable);
}
