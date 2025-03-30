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
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.interview.models.CreateInterviewCommentDto;
import ru.hits.internship.interview.models.UpdateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;
import ru.hits.internship.interview.service.InterviewCommentService;

import java.util.UUID;

@RestController
@Tag(name = "Комментарии к отборам", description = "Отвечает за работу с комментариями к отборам")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/interview/{interviewId}/comment")
public class InterviewCommentController {

    private final InterviewCommentService interviewCommentService;

    @Operation(summary = "Создать комментарий к отбору")
    //ID автора берется из токена
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public InterviewCommentDto createInterviewComment(
            @RequestParam("id") @Parameter(description = "Id автора") UUID authorId,
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @Valid @RequestBody CreateInterviewCommentDto createInterviewCommentDto
    ) {
        //TODO нужно проверить, что у пользователя есть доступ к отбору (т.е. пользователь - либо студент, создавший отбор, либо деканат) (feature/#3932?)
        //TODO нужно получить ID автора (пользователя) из токена (feature/#3932?)
        return interviewCommentService.createComment(authorId, interviewId, createInterviewCommentDto);
    }

    @Operation(summary = "Обновить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{commentId}")
    public InterviewCommentDto updateInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @PathVariable @Parameter(description = "Id комментария") UUID commentId,
            @Valid @RequestBody UpdateInterviewCommentDto updateInterviewCommentDto
    ) {
        //TODO нужно проверить, что у пользователя есть доступ к комментарию (т.е. пользователь - автор комментария) (feature/#3932?)
        return interviewCommentService.updateComment(commentId, updateInterviewCommentDto);
    }

    @Operation(summary = "Удалить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{commentId}")
    public Response deleteInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @PathVariable @Parameter(description = "Id комментария") UUID commentId
    ) {
        //TODO нужно проверить, что у пользователя есть доступ к комментарию (т.е. пользователь - автор комментария) (feature/#3932?)
        interviewCommentService.deleteComment(commentId);

        return new Response("Комментарий успешно удален", HttpStatus.OK.value());
    }

    @Operation(summary = "Получить список комментариев к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<InterviewCommentDto> getInterviewCommentList(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        //TODO нужно проверить, что у пользователя есть доступ к отбору (т.е. пользователь - либо студент, создавший отбор, либо деканат) (feature/#3932?)
        return interviewCommentService.getCommentList(interviewId, pageable);
    }
}
