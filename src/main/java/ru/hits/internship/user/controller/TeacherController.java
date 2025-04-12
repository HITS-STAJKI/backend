package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.user.model.dto.role.request.create.TeacherCreateDto;
import ru.hits.internship.user.model.dto.role.response.TeacherDto;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Преподаватель", description = "Отвечает за работу с преподавателями")
@RequestMapping(value = "/api/v1/teacher")
public class TeacherController {

    @Operation(summary = "Получение информации о преподавателе текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public TeacherDto getCurrentTeacher() {
        return null;
    }

    @Operation(summary = "Получение информации о преподавателе")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public TeacherDto getTeacherById(@PathVariable UUID id) {
        return null;
    }

    @Operation(summary = "Получение всех преподавателей")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<TeacherDto> getAllTeachers() {
        return null;
    }

    @Operation(summary = "Создание преподавателя")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public TeacherDto createTeacher(@RequestBody @Valid TeacherCreateDto createDto) {
        return null;
    }
}
