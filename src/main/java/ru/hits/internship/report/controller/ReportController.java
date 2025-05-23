package ru.hits.internship.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.report.models.ReportDto;
import ru.hits.internship.report.models.ReportId;
import ru.hits.internship.report.service.report.ReportService;
import ru.hits.internship.report.utils.ReportIdEditor;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

@RestController
@Tag(name = "Practice reports", description = "Отвечает за работу с отчетами")
@RequestMapping(value = "/api/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ReportId.class, new ReportIdEditor());
    }

    @GetMapping
    @Operation(
            summary = "Получить отчет о практике",
            description = "Позволяет получить информацию об отчете конкретной практики"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("@practiceAccessFacade.isOwner(#authUser.id(), #practiceId) or hasAnyRole('DEAN', 'CURATOR')")
    public ReportDto getPracticeReport(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam("practiceId") @Parameter(description = "Идентификатор практики") UUID practiceId
    ) {
        return reportService.getPracticeReport(practiceId);
    }

    @PostMapping("/file/attach")
    @Operation(
            summary = "Прикрепить файл к отчету",
            description = "Позволяет прикрепить файл к отчету"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('STUDENT')")
    public ReportDto attachFileToReport(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam("reportId") @Parameter(description = "Идентификатор отчета") ReportId reportId,
            @RequestParam("fileId") @Parameter(description = "Идентификатор файла") UUID fileId
    ) {
        return reportService.attachFile(authUser, reportId, fileId);
    }

    @PostMapping("/file/unattach")
    @Operation(
            summary = "Открепить файл от отчета",
            description = "Позволяет открепить файл от отчета"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('STUDENT')")
    public ReportDto unattachFileFromReport(
            @RequestParam("reportId") @Parameter(description = "Идентификатор отчета") ReportId reportId
    ) {
        return reportService.unAttachFile(reportId);
    }

    @PatchMapping("/grade")
    @Operation(
            summary = "Изменить оценку отчета о практике",
            description = "Позволяет изменить оценку отчета конкретной практики"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('DEAN', 'CURATOR')")
    public ReportDto setGrade(
            @RequestParam("reportId") @Parameter(description = "Идентификатор отчета") ReportId reportId,
            @RequestParam("grade") @Parameter(description = "Оценка отчета") Integer grade
    ) {
        if (grade < 2 || grade > 5) {
            throw new BadRequestException("Оценка должна быть целым числом от 2 до 5");
        }

        return reportService.setGrade(reportId, grade);
    }
}
