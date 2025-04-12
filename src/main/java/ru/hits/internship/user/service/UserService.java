package ru.hits.internship.user.service;

import ru.hits.internship.user.model.dto.user.UserDetailsDto;

import java.util.UUID;

public interface UserService {
    UserDetailsDto getUserDetailsById(UUID id);
}
