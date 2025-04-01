package ru.hits.internship.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.InvalidCredentialException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.security.JwtService;
import ru.hits.internship.user.UserMapper;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.dto.auth.LoginCredentialsDto;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.auth.TokenDto;
import ru.hits.internship.user.model.dto.user.UserDto;
import ru.hits.internship.user.repository.UserRepository;
import ru.hits.internship.user.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper.INSTANCE::toDto)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, email));
    }

    @Override
    public TokenDto login(LoginCredentialsDto credentials) {
        UserEntity userEntity = userRepository.findByEmail(credentials.email())
                .orElseThrow(InvalidCredentialException::new);

        if (!passwordEncoder.matches(credentials.password(), userEntity.getPassword())) {
            throw new InvalidCredentialException();
        }

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

        userRepository.save(userEntity);
        return jwtService.generateAccessToken(userEntity);
    }
}
