package ru.hits.internship.report.service.report;

import ru.hits.internship.report.models.ReportDto;
import ru.hits.internship.report.models.ReportId;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

public interface ReportService {
    ReportDto getPracticeReport(UUID practiceId);

    ReportDto setGrade(ReportId reportId, Integer grade);

    ReportDto attachFile(AuthUser user, ReportId reportId, UUID fileId);

    ReportDto unAttachFile(ReportId reportId);
}
