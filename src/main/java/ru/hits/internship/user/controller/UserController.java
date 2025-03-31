package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.UserMapper;
import ru.hits.internship.user.entity.UserEntity;
import ru.hits.internship.user.models.auth.LoginCredentialsDto;
import ru.hits.internship.user.models.auth.RegistrationRequestDto;
import ru.hits.internship.user.models.auth.TokenDto;
import ru.hits.internship.user.models.role.UserRole;
import ru.hits.internship.user.models.user.UserDto;
import ru.hits.internship.user.models.user.UserEditDto;
import ru.hits.internship.user.service.UserService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Tag(name = "Пользователь", description = "Отвечает за работу с пользователем")
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Вход в аккаунт")
    @PostMapping("/login")
    public TokenDto login(@RequestBody @Valid LoginCredentialsDto credentials) {
        return userService.login(credentials);
    }

    @Operation(summary = "Регистрация")
    @PostMapping("/register")
    public TokenDto register(@RequestBody @Valid RegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @Operation(summary = "Изменение информации текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public UserDto updateCurrentUser(@RequestBody @Valid UserEditDto editDto) {
        return null;
    }

    @Operation(summary = "Получение информации пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение информации текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public UserDto getCurrentUser(@AuthenticationPrincipal UserEntity user) {
        return null;
    }

    @Operation(summary = "Получение списка пользователей")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<UserDto> getUserList(
            @RequestParam(required = false) Optional<UserRole> userRole,
            @ParameterObject Pageable pageable
    ) {
        return null;
    }
}
