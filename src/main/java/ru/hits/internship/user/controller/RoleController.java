package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.user.models.role.request.create.CuratorCreateDto;
import ru.hits.internship.user.models.role.request.create.DeanCreateDto;
import ru.hits.internship.user.models.role.request.create.StudentCreateDto;
import ru.hits.internship.user.models.role.request.create.TeacherCreateDto;
import ru.hits.internship.user.models.role.request.edit.CuratorEditDto;
import ru.hits.internship.user.models.role.request.edit.StudentEditDto;
import ru.hits.internship.user.models.role.response.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Роль", description = "Отвечает за работу с ролями пользователей")
@RequestMapping(value = "/api/v1/role")
public class RoleController {

    @Operation(summary = "Удаление роли пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{roleId}")
    public void deleteUserRole(@PathVariable UUID roleId) {}

    @Operation(summary = "Изменение роли куратора")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/curator")
    public void updateCuratorRole(@PathVariable String id, @RequestBody @Valid CuratorEditDto editDto) {}

    @Operation(summary = "Изменение роли студента")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/student")
    public void updateStudentRole(@PathVariable String id, @RequestBody @Valid StudentEditDto editDto) {}

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

    @Operation(summary = "Получение списка ролей пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("user/{id}")
    public List<RoleResponseDto> getUserRoles(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение роли деканата")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/dean")
    public DeanResponseDto getDeanRole(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение роли преподавателя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/teacher")
    public TeacherResponseDto getTeacherRole(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение роли куратора")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/curator")
    public CuratorResponseDto getCuratorRole(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение роли студента")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/student")
    public StudentResponseDto getStudentRole(@PathVariable UUID id) {
        return null;
    }
}
