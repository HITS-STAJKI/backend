package ru.hits.internship.interview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.common.models.Pagination.PagedListDto;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.UpdateInterviewDto;

import java.util.UUID;

@RestController
@Tag(name = "Отборы", description = "Отвечает за работу с отборами")
@RequestMapping(value = "/api/v1/interview")
public class InterviewController {
    @Operation(summary = "Создать отбор", description = "Запрос доступен для студентов")
    //ID студента берется из токена
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public InterviewDto createInterview(@Valid @RequestBody CreateInterviewDto createInterviewDto) {
        return null;
    }

    @Operation(summary = "Обновить отбор")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{interviewId}")
    public InterviewDto updateInterview(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @Valid @RequestBody UpdateInterviewDto updateInterviewDto
    ) {
        return null;
    }

    @Operation(summary = "Удалить отбор")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{interviewId}")
    public void deleteInterview(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId
    ) {
    }

    @Operation(summary = "Получить отбор")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{interviewId}")
    public InterviewDto getInterview(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId
    ) {
        return null;
    }

    @Operation(summary = "Получить список отборов", description = "Запрос доступен для деканата")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{studentId}/list")
    public PagedListDto<InterviewDto> getInterviewList(
            @PathVariable @Parameter(description = "Id студента") UUID studentId,
            @ParameterObject @PageableDefault(direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return null;
    }

    @Operation(summary = "Получить список отборов", description = "Запрос доступен для студентов")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<InterviewDto> getInterviewList(
            @ParameterObject @PageableDefault(direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return null;
    }

}
