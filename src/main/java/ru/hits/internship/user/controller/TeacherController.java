package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.TeacherCreateDto;
import ru.hits.internship.user.model.dto.role.response.TeacherDto;

@RestController
@Tag(name = "Преподаватель", description = "Отвечает за работу с преподавателями")
@RequestMapping(value = "/api/v1/teacher")
public class TeacherController {

    @Operation(summary = "Получение всех преподавателей")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<TeacherDto> getAllTeachers(@ParameterObject Pageable pageable) {
        return null;
    }

    @Operation(summary = "Создание преподавателя")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public TeacherDto createTeacher(@RequestBody @Valid TeacherCreateDto createDto) {
        return null;
    }
}
