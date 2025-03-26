package ru.hits.internship.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.practice.models.CreatePracticeDto;
import ru.hits.internship.practice.models.PracticeDto;
import ru.hits.internship.practice.models.UpdatePracticeDto;
import ru.hits.internship.practice.service.PracticeService;

import java.util.UUID;

@Tag(name = "Практики", description = "Отвечает за работу с практиками студентов")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/practice")
public class PracticeController {
    private final PracticeService practiceService;

    @Operation(
            summary = "Получение информации о практике студента",
            description = "Позволяет получить информацию о практике студента"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    //TODO("Добавить получение студента из Principal")
    public PracticeDto getCurrentStudentPractice(
            @RequestParam("id") @Parameter(description = "Id студента") UUID studentId
    ) {
        return practiceService.getStudentCurrentPractice(studentId);
    }

    @Operation(
            summary = "Получить список практик студента",
            description = "Позволяет получить полный список практик студента с пагинацией (вместе с архивированными)"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list/all")
    public PagedListDto<PracticeDto> getStudentPractices(
            @RequestParam("id") @Parameter(description = "Id студента") UUID studentId,
            @ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return practiceService.getStudentPractices(studentId, pageable);
    }

    @Operation(
            summary = "Подача заявки на практику студента",
            description = "Позволяет подать заявку на практику студента"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public PracticeDto createStudentPractice(@RequestBody @Valid CreatePracticeDto createPracticeDto) {
        return practiceService.createStudentPractice(createPracticeDto);
    }

    @Operation(
            summary = "Подтверждение практики студента",
            description = "Позволяет куратору подтвердить практику студента"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/approve")
    public PracticeDto approveStudentPractice(
            @RequestParam("id") @Parameter(description = "Id практики") UUID practiceId
    ) {
        return practiceService.approveStudentPractice(practiceId);
    }

    @Operation(
            summary = "Получить заявки на практику",
            description = "Позволяет получить список заявок на практику с пагинацией"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list/unapproved")
    public PagedListDto<PracticeDto> getPracticeRequests(
            @ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return practiceService.getPracticeRequests(pageable);
    }

    @Operation(
            summary = "Обновление практики",
            description = "Позволяет обновить информацию о практике"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public PracticeDto updatePractice(
            @RequestParam("id") @Parameter(description = "Id практики") UUID practiceId,
            @RequestBody @Valid UpdatePracticeDto updatePracticeDto
    ) {
        return practiceService.updatePractice(practiceId, updatePracticeDto);
    }

    @Operation(
            summary = "Удаление практики студента",
            description = "Позволяет куратору удалить практику студента"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/archive")
    public PracticeDto archiveStudentPractice(
            @RequestParam("id") @Parameter(description = "Id практики") UUID practiceId
    ) {
        return practiceService.archiveStudentPractice(practiceId);
    }
}
