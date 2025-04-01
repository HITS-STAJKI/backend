package ru.hits.internship.user.service;

import ru.hits.internship.user.model.dto.auth.LoginCredentialsDto;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.auth.TokenDto;
import ru.hits.internship.user.model.dto.user.AuthUser;

public interface UserService {
    AuthUser getAuthUserByEmail(String email);
    TokenDto login(LoginCredentialsDto credentials);
    TokenDto register(RegistrationRequestDto registrationDto);
}
