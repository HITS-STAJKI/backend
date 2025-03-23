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
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import java.util.UUID;

@RestController
@Tag(name = "Комментарии к отборам", description = "Отвечает за работу с комментариями к отборам")
@RequestMapping(value = "/api/v1/interview/{interviewId}/comment")
public class InterviewCommentController {
    @Operation(summary = "Создать комментарий к отбору")
    //ID автора берется из токена
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public InterviewCommentDto createInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @Valid @RequestBody CreateInterviewCommentDto createInterviewCommentDto
    ) {
        return null;
    }

    @Operation(summary = "Обновить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{commentId}")
    public InterviewCommentDto updateInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @PathVariable @Parameter(description = "Id комментария") UUID commentId,
            @Valid RequestBody UpdateInterviewCommentDto updateInterviewCommentDto
    ) {
        return null;
    }

    @Operation(summary = "Удалить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{commentId}")
    public Response deleteInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @PathVariable @Parameter(description = "Id комментария") UUID commentId
    ) {
        return null;
    }

    @Operation(summary = "Получить список комментариев к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<InterviewCommentDto> getInterviewCommentList(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return null;
    }
}
