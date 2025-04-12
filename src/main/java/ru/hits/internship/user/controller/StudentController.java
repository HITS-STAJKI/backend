package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.user.model.dto.role.request.create.StudentCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.StudentEditDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;

import java.util.List;

@RestController
@Tag(name = "Студент", description = "Отвечает за работу со студентами")
@RequestMapping(value = "/api/v1/student")
public class StudentController {

    @Operation(summary = "Получение всех студентов")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<StudentDto> getAllStudents() {
        return null;
    }

    @Operation(summary = "Создание студента")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public StudentDto createStudent(@RequestBody @Valid StudentCreateDto createDto) {
        return null;
    }

    @Operation(summary = "Обновление информации о студенте")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public StudentDto updateStudent(
            @PathVariable String id,
            @RequestBody @Valid StudentEditDto editDto
    ) {
        return null;
    }
}
