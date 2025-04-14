package ru.hits.internship.user.service;

import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.model.dto.auth.LoginCredentialsDto;
import ru.hits.internship.user.model.dto.auth.PasswordEditDto;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.auth.TokenDto;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

public interface AuthService {
    AuthUser getAuthUserByEmail(String email);
    TokenDto login(LoginCredentialsDto credentials);
    TokenDto register(RegistrationRequestDto registrationDto);
    Response updatePassword(UUID userId, PasswordEditDto editDto);
}