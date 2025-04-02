package ru.hits.internship.report.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.ForbiddenException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.report.entity.ReportCommentEntity;
import ru.hits.internship.report.entity.ReportEntity;
import ru.hits.internship.report.mapper.ReportCommentMapper;
import ru.hits.internship.report.models.CreateReportCommentDto;
import ru.hits.internship.report.models.ReportCommentDto;
import ru.hits.internship.report.models.UpdateReportCommentDto;
import ru.hits.internship.report.repository.ReportCommentRepository;
import ru.hits.internship.report.repository.ReportRepository;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.repository.UserRepository;

import java.util.UUID;

import static ru.hits.internship.user.utils.RoleChecker.isUserHasRole;

@Service
@RequiredArgsConstructor
public class ReportCommentServiceImpl implements ReportCommentService {
    private final ReportRepository reportRepository;
    private final ReportCommentRepository reportCommentRepository;
    private final UserRepository userRepository;
    private final ReportCommentMapper mapper;

    @Override
    public ReportCommentDto createReportComment(AuthUser user, UUID reportId, CreateReportCommentDto createReportCommentDto) {
        var reportEntity = reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException(ReportEntity.class, reportId));

        if (!isUserAuthor(reportEntity, user)
                && !isUserHasRole(user, UserRole.DEAN)
                && !isUserHasRole(user, UserRole.CURATOR)) {
            throw new ForbiddenException();
        }

        UserEntity author = userRepository.findById(user.id())
                .orElseThrow(() -> new NotFoundException(UserEntity.class, user.id()));

        ReportCommentEntity reportCommentEntity = mapper.toEntity(createReportCommentDto, author, reportEntity);

        reportCommentEntity = reportCommentRepository.save(reportCommentEntity);

        return mapper.toDto(reportCommentEntity);
    }

    @Override
    public ReportCommentDto updateReportComment(AuthUser user, UpdateReportCommentDto updateReportCommentDto, UUID commentId) {
        var reportCommentEntity = reportCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ReportCommentDto.class, commentId));

        if (!isUserAuthor(reportCommentEntity, user)) {
            throw new ForbiddenException();
        }

        reportCommentEntity.setContent(updateReportCommentDto.getContent());
        var savedEntity = reportCommentRepository.save(reportCommentEntity);

        return mapper.toDto(savedEntity);
    }

    @Override
    public Response deleteReportComment(AuthUser user, UUID commentId) {
        var reportCommentEntity = reportCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ReportCommentDto.class, commentId));

        if (!isUserAuthor(reportCommentEntity, user)) {
            throw new ForbiddenException();
        }

        reportCommentRepository.deleteById(commentId);

        return new Response(
                "Операция по удалению выполнена",
                HttpStatus.OK.value()
        );
    }

    @Override
    public PagedListDto<ReportCommentDto> getReportCommentList(AuthUser user, UUID reportId, Pageable pageable) {
        var reportEntity = reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException(ReportCommentDto.class, reportId));

        if (!isUserAuthor(reportEntity, user)
                && !isUserHasRole(user, UserRole.DEAN)
                && !isUserHasRole(user, UserRole.CURATOR)
        ) {
            throw new ForbiddenException();
        }

        return new PagedListDto<>(
                reportCommentRepository.findAllByReportId(reportId, pageable)
                        .map(mapper::toDto)
        );
    }

    private Boolean isUserAuthor(ReportCommentEntity reportComment, AuthUser user) {
        return reportComment.getAuthor().getId().equals(user.id());
    }

    private Boolean isUserAuthor(ReportEntity reportEntity, AuthUser user) {
        return reportEntity.getAuthor().getId().equals(user.id());
    }
}
