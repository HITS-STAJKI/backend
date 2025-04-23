package ru.hits.internship.group.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.group.dto.ChangeStudentGraduationStatusDto;
import ru.hits.internship.group.service.GraduationService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/graduation")
@RequiredArgsConstructor
@Tag(name = "Выпуск потоков студентов", description = "Отвечает за работу с выпуском потоков")
public class GraduationController {
    private final GraduationService graduationService;

    @PostMapping("/{groupId}")
    @Operation(
            summary = "Выпуск потока",
            description = "Позволяет выпустить весь поток студентов и заархивировать их практики"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    public Response graduateGroup(@Parameter(description = "Идентификатор потока") @PathVariable UUID groupId) {
        return graduationService.graduateGroup(groupId);
    }

    @PostMapping
    @Operation(
            summary = "Изменение статуса выпуска конкретного студента",
            description = "Позволяет изменить статус выпуска конкретного студента"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    public Response changeStudentGraduationStatus(@Validated @RequestBody ChangeStudentGraduationStatusDto dto) {
        return graduationService.changeStudentGraduationStatus(dto);
    }
}
