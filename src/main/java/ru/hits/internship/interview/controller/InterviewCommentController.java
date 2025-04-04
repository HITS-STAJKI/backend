package ru.hits.internship.interview.controller;

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
import ru.hits.internship.interview.models.CreateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;
import ru.hits.internship.interview.models.UpdateInterviewCommentDto;
import ru.hits.internship.interview.service.InterviewCommentService;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

@RestController
@Tag(name = "Комментарии к отборам", description = "Отвечает за работу с комментариями к отборам")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/interview/{interviewId}/comment")
public class InterviewCommentController {

    private final InterviewCommentService interviewCommentService;

    @Operation(summary = "Создать комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public InterviewCommentDto createInterviewComment(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @Valid @RequestBody CreateInterviewCommentDto createInterviewCommentDto
    ) {
        return interviewCommentService.createComment(user, interviewId, createInterviewCommentDto);
    }

    @Operation(summary = "Обновить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{commentId}")
    public InterviewCommentDto updateInterviewComment(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @PathVariable @Parameter(description = "Id комментария") UUID commentId,
            @Valid @RequestBody UpdateInterviewCommentDto updateInterviewCommentDto
    ) {
        return interviewCommentService.updateComment(user, commentId, updateInterviewCommentDto);
    }

    @Operation(summary = "Удалить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{commentId}")
    public Response deleteInterviewComment(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @PathVariable @Parameter(description = "Id комментария") UUID commentId
    ) {
        interviewCommentService.deleteComment(user, commentId);

        return new Response("Комментарий успешно удален", HttpStatus.OK.value());
    }

    @Operation(summary = "Получить список комментариев к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<InterviewCommentDto> getInterviewCommentList(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return interviewCommentService.getCommentList(user, interviewId, pageable);
    }
}
