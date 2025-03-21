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
import ru.hits.internship.user.models.role.request.CuratorCreateDto;
import ru.hits.internship.user.models.role.request.DeanCreateDto;
import ru.hits.internship.user.models.role.request.StudentCreateDto;
import ru.hits.internship.user.models.role.request.TeacherCreateDto;
import ru.hits.internship.user.models.role.response.CuratorResponseDto;
import ru.hits.internship.user.models.role.response.DeanResponseDto;
import ru.hits.internship.user.models.role.response.RoleResponseDto;
import ru.hits.internship.user.models.role.response.TeacherResponseDto;
import ru.hits.internship.user.models.user.UserResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name = "Роль", description = "Отвечает за работу с ролями пользователей")
@RequestMapping(value = "/api/v1/role")
public class RoleController {

    @Operation(summary = "Получение списка ролей пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("user/{id}")
    public List<RoleResponseDto> getUserRoles(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Удаление роли пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{roleId}/user/{userId}")
    public void deleteUserRole(@PathVariable UUID roleId, @PathVariable UUID userId) {}

    @Operation(summary = "Создание роли деканата")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("dean")
    public DeanResponseDto createDeanRole(@RequestBody @Valid DeanCreateDto createDto) {
        return null;
    }

    @Operation(summary = "Создание роли преподавателя")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("teacher")
    public TeacherResponseDto createTeacherRole(@RequestBody @Valid TeacherCreateDto createDto) {
        return null;
    }

    @Operation(summary = "Создание роли куратора")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("curator")
    public CuratorResponseDto createCuratorRole(@RequestBody @Valid CuratorCreateDto createDto) {
        return null;
    }

    @Operation(summary = "Создание роли студента")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("student")
    public StudentCreateDto createCuratorRole(@RequestBody @Valid StudentCreateDto createDto) {
        return null;
    }
}
