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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.TeacherCreateDto;
import ru.hits.internship.user.model.dto.role.response.TeacherDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.service.TeacherService;

@RestController
@Tag(name = "Teacher", description = "Отвечает за работу с преподавателями")
@RequestMapping(value = "/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "Получение всех преподавателей")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('DEAN', 'EDUCATIONAL_PROGRAM_LEAD')")
    @GetMapping("/list")
    public PagedListDto<TeacherDto> getAllTeachers(
            @AuthenticationPrincipal AuthUser authUser,
            @ParameterObject Pageable pageable,
            @RequestParam(required = false) String fullName
    ) {
        return teacherService.getAllTeachers(authUser.id(), fullName, pageable);
    }

    @Operation(summary = "Создание преподавателя")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PostMapping
    public TeacherDto createTeacher(@RequestBody @Valid TeacherCreateDto createDto) {
        return teacherService.createTeacher(createDto);
    }
}
