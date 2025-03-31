package ru.hits.internship.user.service;

import ru.hits.internship.user.models.auth.LoginCredentialsDto;
import ru.hits.internship.user.models.auth.RegistrationRequestDto;
import ru.hits.internship.user.models.auth.TokenDto;

public interface UserService {
    TokenDto login(LoginCredentialsDto credentials);
    TokenDto register(RegistrationRequestDto registrationDto);
}
