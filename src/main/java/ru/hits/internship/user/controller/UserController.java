package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.user.models.UserDto;

@RestController
@Tag(name = "Пользователь", description = "Отвечает за работу с пользователем")
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Operation(
            summary = "Получение информации пользователя",
            description = "Позволяет проверить, существует ли пользователь с указанной почтой и получить информацию о нем"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/profile")
    public UserDto getUserProfile(
            @RequestParam(name = "email") @Parameter(description = "Почта пользователя") String email
    ) {
        return null;
    }
}
