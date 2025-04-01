package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.model.dto.role.request.create.CuratorCreateDto;
import ru.hits.internship.user.model.dto.role.request.create.DeanCreateDto;
import ru.hits.internship.user.model.dto.role.request.create.StudentCreateDto;
import ru.hits.internship.user.model.dto.role.request.create.TeacherCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.CuratorEditDto;
import ru.hits.internship.user.model.dto.role.request.edit.StudentEditDto;
import ru.hits.internship.user.model.dto.role.response.CuratorDto;
import ru.hits.internship.user.model.dto.role.response.DeanDto;
import ru.hits.internship.user.model.dto.role.response.RoleDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;
import ru.hits.internship.user.model.dto.role.response.TeacherDto;

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

    @Operation(summary = "Обновление информации о кураторе")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/curator")
    public CuratorDto updateCuratorRole(
            @PathVariable String id,
            @RequestBody @Valid CuratorEditDto editDto
    ) {
        return null;
    }

    @Operation(summary = "Обновление информации о студенте")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/student")
    public StudentDto updateStudentRole(
            @PathVariable String id,
            @RequestBody @Valid StudentEditDto editDto
    ) {
        return null;
    }

    @Operation(summary = "Создание представителя деканата")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("dean")
    public DeanDto createDeanRole(@RequestBody @Valid DeanCreateDto createDto) {
        return null;
    }

    @Operation(summary = "Создание преподавателя")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("teacher")
    public TeacherDto createTeacherRole(@RequestBody @Valid TeacherCreateDto createDto) {
        return null;
    }

    @Operation(summary = "Создание куратора")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("curator")
    public CuratorDto createCuratorRole(@RequestBody @Valid CuratorCreateDto createDto) {
        return null;
    }

    @Operation(summary = "Создание студента")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("student")
    public StudentDto createStudentRole(@RequestBody @Valid StudentCreateDto createDto) {
        return null;
    }

    @Operation(summary = "Получение списка ролей пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("user/{id}")
    public List<RoleDto> getUserRoles(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение информации о представителе деканата")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/dean")
    public DeanDto getDeanRole(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение информации о преподавателе")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/teacher")
    public TeacherDto getTeacherRole(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение информации о кураторе")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/curator")
    public CuratorDto getCuratorRole(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение информации о студенте")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/student")
    public StudentDto getStudentRole(@PathVariable UUID id) {
        return null;
    }
}
