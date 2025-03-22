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
import ru.hits.internship.interview.models.CreateUpdateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;

@RestController
@Tag(name = "Комментарии к отборам", description = "Отвечает за работу с комментариями к отборам")
@RequestMapping(value = "/api/v1/interview/{interviewId}/comment")
public class InterviewCommentController {
    @Operation(summary = "Создать комментарий к отбору")
    //ID автора берется из токена
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public InterviewCommentDto createInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") String interviewId,
            @Valid @RequestBody CreateUpdateInterviewCommentDto createUpdateInterviewCommentDto
    ) {
        return null;
    }

    @Operation(summary = "Обновить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{commentId}")
    public InterviewCommentDto updateInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") String interviewId,
            @PathVariable @Parameter(description = "Id комментария") String commentId,
            @Valid @RequestBody CreateUpdateInterviewCommentDto createUpdateInterviewCommentDto
    ) {
        return null;
    }

    @Operation(summary = "Удалить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{commentId}")
    public void deleteInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") String interviewId,
            @PathVariable @Parameter(description = "Id комментария") String commentId
    ) {
    }

    @Operation(summary = "Получить список комментариев к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<InterviewCommentDto> getInterviewCommentList(
            @PathVariable @Parameter(description = "Id отбора") String interviewId,
            @ParameterObject @PageableDefault(direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return null;
    }
}
