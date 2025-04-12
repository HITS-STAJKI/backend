package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.model.dto.role.response.RoleDto;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Роль", description = "Отвечает за работу с ролями пользователей")
@RequestMapping(value = "/api/v1/role")
public class RoleController {

    @Operation(summary = "Удаление роли пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{roleId}")
    public Response deleteUserRole(@PathVariable UUID roleId) {
        return null;
    }

    @Operation(summary = "Получение списка ролей пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("user/{id}")
    public List<RoleDto> getUserRoles(@PathVariable UUID id) {
        return null;
    }
}
