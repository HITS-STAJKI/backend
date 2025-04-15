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
import ru.hits.internship.user.model.dto.role.request.create.StudentCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.StudentEditDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.service.StudentService;

import java.util.UUID;

@RestController
@Tag(name = "Студент", description = "Отвечает за работу со студентами")
@RequestMapping(value = "/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Создание студента для текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public StudentDto createStudentForCurrentUser(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody @Valid StudentCreateDto createDto
    ) {
        return studentService.createStudent(authUser.id(), createDto);
    }

    @Operation(summary = "Создание студента")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PostMapping("user/{userId}")
    public StudentDto createStudent(
            @PathVariable UUID userId,
            @RequestBody @Valid StudentCreateDto createDto
    ) {
        return studentService.createStudent(userId, createDto);
    }

    @Operation(summary = "Обновление информации о студенте")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PutMapping("/{id}")
    public StudentDto updateStudent(
            @PathVariable UUID id,
            @RequestBody @Valid StudentEditDto editDto
    ) {
        return studentService.updateStudent(id, editDto);
    }

    @Operation(summary = "Получение всех студентов")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @GetMapping("/list")
    public PagedListDto<StudentDto> getAllStudents(
            @AuthenticationPrincipal AuthUser authUser,
            @ParameterObject Pageable pageable,
            @RequestParam(required = false) UUID groupId
    ) {
        return studentService.getAllStudents(authUser.id(), pageable);
    }
}
