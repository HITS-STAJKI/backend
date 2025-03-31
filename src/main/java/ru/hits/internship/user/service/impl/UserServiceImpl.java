package ru.hits.internship.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.security.jwt.JwtService;
import ru.hits.internship.user.UserMapper;
import ru.hits.internship.user.entity.UserEntity;
import ru.hits.internship.user.models.auth.LoginCredentialsDto;
import ru.hits.internship.user.models.auth.RegistrationRequestDto;
import ru.hits.internship.user.models.auth.TokenDto;
import ru.hits.internship.user.repository.UserRepository;
import ru.hits.internship.user.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public TokenDto login(LoginCredentialsDto credentials) {
        return null;
    }

    @Override
    public TokenDto register(RegistrationRequestDto registrationDto) {
        String email = registrationDto.email();
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("User with " + email + " already exists");
        }

        UserEntity userEntity = UserMapper.INSTANCE.toEntity(registrationDto);
        userRepository.save(userEntity);

        return jwtService.generateAccessToken(userEntity);
    }
}
