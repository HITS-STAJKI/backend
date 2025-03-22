package ru.hits.internship.interview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.interview.models.CreateUpdateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;

import java.util.List;

@RestController
@Tag(name = "Комментарии к отборам", description = "Отвечает за работу с комментариями к отборам")
@RequestMapping(value = "/api/v1/interview/{interviewId}/comment")
public class InterviewCommentController {
    @Operation(description = "Создать комментарий к отбору")
    //ID автора берется из токена
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public InterviewCommentDto createInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") String interviewId,
            @RequestBody CreateUpdateInterviewCommentDto createUpdateInterviewCommentDto
    ) {
        return null;
    }

    @Operation(description = "Обновить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{commentId}")
    public InterviewCommentDto updateInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") String interviewId,
            @PathVariable @Parameter(description = "Id комментария") String commentId,
            @RequestBody CreateUpdateInterviewCommentDto createUpdateInterviewCommentDto
    ) {
        return null;
    }

    @Operation(description = "Удалить комментарий к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{commentId}")
    public void deleteInterviewComment(
            @PathVariable @Parameter(description = "Id отбора") String interviewId,
            @PathVariable @Parameter(description = "Id комментария") String commentId
    ) {
    }

    @Operation(description = "Получить список комментариев к отбору")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<InterviewCommentDto> getInterviewCommentList(
            @PathVariable @Parameter(description = "Id отбора") String interviewId,
            @RequestParam(name = "pageNumber") @Parameter(description = "Номер страницы; начинается с нуля", required = true) int pageNumber,
            @RequestParam(name = "pageSize") @Parameter(description = "Размер страницы", required = true) int pageSize
    ) {
        return null;
    }
}
