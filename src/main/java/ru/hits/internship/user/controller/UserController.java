package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.common.models.Pagination.PagedListDto;
import ru.hits.internship.common.models.Pagination.PaginationParameters;
import ru.hits.internship.user.models.role.UserRole;
import ru.hits.internship.user.models.auth.LoginCredentialsDto;
import ru.hits.internship.user.models.auth.RegistrationRequestDto;
import ru.hits.internship.user.models.auth.TokenResponseDto;
import ru.hits.internship.user.models.user.UserEditDto;
import ru.hits.internship.user.models.user.UserDto;

import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name = "Пользователь", description = "Отвечает за работу с пользователем")
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Operation(summary = "Вход в аккаунт")
    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid LoginCredentialsDto credentials) {
        return null;
    }

    @Operation(summary = "Регистрация")
    @PostMapping("/register")
    public TokenResponseDto register(@RequestBody @Valid RegistrationRequestDto requestDto) {
        return null;
    }

    @Operation(summary = "Изменение информации текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public void updateCurrentUser(@RequestBody @Valid UserEditDto editDto) {}

    @Operation(summary = "Получение информации пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение информации текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public UserDto getCurrentUser() {
        return null;
    }

    @Operation(summary = "Получение списка пользователей")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<UserDto> getUserList(
            @RequestParam(required = false) Optional<UserRole> userRole,
            @ParameterObject PaginationParameters pageable
    ) {
        return null;
    }
}
