package ru.hits.internship.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.InvalidCredentialException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.security.JwtService;
import ru.hits.internship.user.mapper.UserMapper;
import ru.hits.internship.user.model.dto.auth.LoginCredentialsDto;
import ru.hits.internship.user.model.dto.auth.PasswordEditDto;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.auth.TokenDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.repository.UserRepository;
import ru.hits.internship.user.service.AuthService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthUser getAuthUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper.INSTANCE::toAuthUser)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, email));
    }

    @Override
    public TokenDto login(LoginCredentialsDto credentials) {
        UserEntity userEntity = userRepository.findByEmail(credentials.email())
                .orElseThrow(InvalidCredentialException::new);

        if (!passwordEncoder.matches(credentials.password(), userEntity.getPassword())) {
            throw new InvalidCredentialException();
        }

        setLastLoginDateAndSave(userEntity);
        return jwtService.generateAccessToken(userEntity);
    }

    @Override
    public TokenDto register(RegistrationRequestDto registrationDto) {
        String email = registrationDto.email();
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("User with " + email + " already exists");
        }

        UserEntity userEntity = UserMapper.INSTANCE.toEntity(registrationDto);
        userEntity.setPassword(passwordEncoder.encode(registrationDto.password()));

        setLastLoginDateAndSave(userEntity);
        return jwtService.generateAccessToken(userEntity);
    }

    @Override
    public Response updatePassword(UUID userId, PasswordEditDto editDto) {
        UserEntity user = userRepository.findByIdOrThrow(userId);

        if (!passwordEncoder.matches(editDto.oldPassword(), user.getPassword())) {
            throw new BadRequestException("Old password doesn't match");
        }
        if (!editDto.newPassword().equals(editDto.repeatNewPassword())) {
            throw new BadRequestException("Passwords don't match");
        }

        user.setPassword(passwordEncoder.encode(editDto.newPassword()));
        userRepository.save(user);

        return new Response("Password changed successfully", HttpStatus.OK.value());
    }

    private void setLastLoginDateAndSave(UserEntity user) {
        user.setLastLoginDate(LocalDateTime.now());
        userRepository.save(user);
    }
}
