package ru.hits.internship.interview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.UpdateInterviewDto;
import ru.hits.internship.interview.service.InterviewService;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

@RestController
@Tag(name = "Отборы", description = "Отвечает за работу с отборами")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/interview")
public class InterviewController {

    private final InterviewService interviewService;

    @Operation(summary = "Создать отбор", description = "Запрос доступен для студентов")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public InterviewDto createInterview(
            @AuthenticationPrincipal AuthUser user,
            @Valid @RequestBody CreateInterviewDto createInterviewDto
    ) {
        return interviewService.createInterview(user, createInterviewDto);
    }

    @Operation(summary = "Обновить отбор")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{interviewId}")
    public InterviewDto updateInterview(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @Valid @RequestBody UpdateInterviewDto updateInterviewDto
    ) {
        return interviewService.updateInterview(user, interviewId, updateInterviewDto);
    }

    @Operation(summary = "Удалить отбор")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{interviewId}")
    public Response deleteInterview(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId
    ) {
        interviewService.deleteInterview(user, interviewId);

        return new Response("Отбор успешно удален", HttpStatus.OK.value());
    }

    @Operation(summary = "Получить отбор")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{interviewId}")
    public InterviewDto getInterview(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId
    ) {
        return interviewService.getInterview(user, interviewId);
    }

    @Operation(summary = "Получить список отборов конкретного студента", description = "Запрос доступен для деканата")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{studentId}/list")
    public PagedListDto<InterviewDto> getInterviewList(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id студента") UUID studentId,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        return interviewService.getInterviewList(user, studentId, pageable);
    }

    @Operation(summary = "Получить список отборов", description = "Запрос доступен для студентов")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<InterviewDto> getInterviewList(
            @AuthenticationPrincipal AuthUser user,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        return interviewService.getInterviewList(user, pageable);
    }

}
