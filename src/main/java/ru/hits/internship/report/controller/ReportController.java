package ru.hits.internship.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.report.models.ReportDto;

import java.util.UUID;

@RestController
@Tag(name = "Отчеты", description = "Отвечает за работу с отчетами")
@RequestMapping(value = "/api/v1/report")
public class ReportController {
    @GetMapping
    @Operation(
            summary = "Получить отчет о практике",
            description = "Позволяет получить информацию об отчете конкретной практики"
    )
    @SecurityRequirement(name = "bearerAuth")
    public ReportDto getPracticeReport(
            @RequestParam("practiceId") @Parameter(description = "Идентификатор практики") UUID practiceId
    ) {
        return null;
    }

    @PostMapping("/file/attach")
    @Operation(
            summary = "Прикрепить файл к отчету",
            description = "Позволяет прикрепить файл к отчету"
    )
    @SecurityRequirement(name = "bearerAuth")
    public ReportDto attachFileToReport(
            @RequestParam("fileId") @Parameter(description = "Идентификатор файла") UUID fileId
    ) {
        return null;
    }

    @PostMapping("/file/unattach")
    @Operation(
            summary = "Открепить файл от отчета",
            description = "Позволяет открепить файл от отчета"
    )
    @SecurityRequirement(name = "bearerAuth")
    public ReportDto unattachFileFromReport() {
        return null;
    }

    @PostMapping("/approve")
    @Operation(
            summary = "Аппрувнуть отчет о практике",
            description = "Позволяет аппрувнуть отчет конкретной практики"
    )
    @SecurityRequirement(name = "bearerAuth")
    public ReportDto approveReport(
            @RequestParam("practiceId") @Parameter(description = "Идентификатор практики") UUID practiceId
    ) {
        return null;
    }

    @PostMapping("/unapprove")
    @Operation(
            summary = "Отменить апрув отчета о практике",
            description = "Позволяет отменить апрув отчета конкретной практики"
    )
    @SecurityRequirement(name = "bearerAuth")
    public ReportDto unapproveReport(
            @RequestParam("practiceId") @Parameter(description = "Идентификатор практики") UUID practiceId
    ) {
        return null;
    }
}
