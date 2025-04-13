package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.service.UserService;

import java.util.UUID;

@RestController
@Tag(name = "Роль", description = "Отвечает за работу с ролями пользователей")
@RequestMapping(value = "/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;

    @Operation(summary = "Удаление роли текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{roleId}")
    public Response deleteCurrentUserRole(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable UUID roleId
    ) {
        return userService.deleteRole(authUser.id(), roleId);
    }

    @Operation(summary = "Удаление роли пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @DeleteMapping("/{roleId}/user/{userId}")
    public Response deleteUserRole(
            @PathVariable UUID roleId,
            @PathVariable UUID userId
    ) {
        return userService.deleteRole(userId, roleId);
    }
}
