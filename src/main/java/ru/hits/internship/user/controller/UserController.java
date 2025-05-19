package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.auth.LoginCredentialsDto;
import ru.hits.internship.user.model.dto.auth.PasswordEditDto;
import ru.hits.internship.user.model.dto.auth.RegistrationRequestDto;
import ru.hits.internship.user.model.dto.auth.TokenDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.dto.user.UserDetailsDto;
import ru.hits.internship.user.model.dto.user.UserDto;
import ru.hits.internship.user.model.dto.user.UserEditDto;
import ru.hits.internship.user.model.dto.user.UserEmailEditDto;
import ru.hits.internship.user.model.dto.user.UserFilter;
import ru.hits.internship.user.model.dto.user.UserShortDto;
import ru.hits.internship.user.service.AuthService;
import ru.hits.internship.user.service.UserService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Tag(name = "User", description = "Отвечает за работу с пользователем")
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "Вход в аккаунт")
    @PostMapping("/login")
    public TokenDto login(@RequestBody @Valid LoginCredentialsDto credentials) {
        return authService.login(credentials);
    }

    @Operation(summary = "Регистрация")
    @PostMapping("/register")
    public TokenDto register(@RequestBody @Valid RegistrationRequestDto requestDto) {
        return authService.register(requestDto);
    }

    @Operation(summary = "Изменение информации текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("!hasRole('STUDENT')")
    @PutMapping
    public UserShortDto updateCurrentUser(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody @Valid UserEditDto editDto
    ) {
        return userService.updateUser(authUser.id(), editDto);
    }

    @Operation(summary = "Изменение пароля текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/password")
    public Response updateCurrentUserPassword(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody @Valid PasswordEditDto editDto
    ) {
        return authService.updatePassword(authUser.id(), editDto);
    }

    @Operation(summary = "Изменение электронной почты указанного пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PutMapping("/{userId}")
    public UserShortDto updateUserEmail(
            @RequestBody @Valid UserEmailEditDto editDto,
            @PathVariable UUID userId
    ) {
        return userService.updateUserEmail(userId, editDto);
    }

    @Operation(summary = "Получение информации текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public UserDetailsDto getCurrentUser(@AuthenticationPrincipal AuthUser authUser) {
        return userService.getUserDetailsById(authUser.id());
    }

    @Operation(summary = "Получение информации пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @GetMapping("/{id}")
    public UserDetailsDto getUserById(@PathVariable UUID id) {
        return userService.getUserDetailsById(id);
    }

    @Operation(summary = "Получение списка пользователей")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @GetMapping("/list")
    public PagedListDto<UserDto> getUserList(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam(required = false) Optional<UserRole> userRole,
            @ParameterObject UserFilter userFilter,
            @ParameterObject Pageable pageable
    ) {
        return userService.getAllUsers(authUser.id(), userFilter, pageable);
    }
}
