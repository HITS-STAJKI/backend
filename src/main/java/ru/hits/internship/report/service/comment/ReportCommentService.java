package ru.hits.internship.report.service.comment;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.report.models.CreateReportCommentDto;
import ru.hits.internship.report.models.ReportCommentDto;
import ru.hits.internship.report.models.UpdateReportCommentDto;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

public interface ReportCommentService {
    ReportCommentDto createReportComment(AuthUser user, UUID reportId, CreateReportCommentDto createReportCommentDto);

    ReportCommentDto updateReportComment(AuthUser user, UpdateReportCommentDto updateReportCommentDto, UUID commentId);

    Response deleteReportComment(AuthUser user, UUID commentId);

    PagedListDto<ReportCommentDto> getReportCommentList(AuthUser user, UUID reportId, Pageable pageable);
}
