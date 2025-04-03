package ru.hits.internship.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.report.models.CreateReportCommentDto;
import ru.hits.internship.report.models.ReportCommentDto;
import ru.hits.internship.report.models.UpdateReportCommentDto;
import ru.hits.internship.report.service.comment.ReportCommentService;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

@RestController
@Tag(name = "Комментарии к отчетам", description = "Отвечает за работу с комментариями к отчетам")
@RequestMapping(value = "/api/v1/report/comment")
@RequiredArgsConstructor
public class ReportCommentController {
    private final ReportCommentService reportCommentService;

    @Operation(summary = "Создать комментарий к отчету")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ReportCommentDto createReportComment(
            @AuthenticationPrincipal AuthUser user,
            @RequestParam @Parameter(description = "Id отчета") UUID reportId,
            @RequestBody CreateReportCommentDto createReportCommentDto
    ) {
        return reportCommentService.createReportComment(user, reportId, createReportCommentDto);
    }

    @Operation(summary = "Обновить комментарий к отчету")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{commentId}")
    public ReportCommentDto updateReportComment(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id комментария") UUID commentId,
            @RequestBody UpdateReportCommentDto updateReportCommentDto
    ) {
        return reportCommentService.updateReportComment(user, updateReportCommentDto, commentId);
    }

    @Operation(summary = "Удалить комментарий к отчету")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{commentId}")
    public Response deleteReportComment(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id комментария") UUID commentId
    ) {
        return reportCommentService.deleteReportComment(user, commentId);
    }

    @Operation(summary = "Получить список комментариев к отчету")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<ReportCommentDto> getReportCommentList(
            @AuthenticationPrincipal AuthUser user,
            @RequestParam @Parameter(description = "Id отчета") UUID reportId,
            @ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return reportCommentService.getReportCommentList(user, reportId, pageable);
    }
}
