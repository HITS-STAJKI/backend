package ru.hits.internship.report.service.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.ForbiddenException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.file.entity.FileEntity;
import ru.hits.internship.file.repository.FileRepository;
import ru.hits.internship.practice.entity.PracticeEntity;
import ru.hits.internship.practice.repository.PracticeRepository;
import ru.hits.internship.report.entity.ReportEntity;
import ru.hits.internship.report.mapper.ReportMapper;
import ru.hits.internship.report.models.ReportDto;
import ru.hits.internship.report.models.ReportId;
import ru.hits.internship.report.repository.ReportRepository;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final PracticeRepository practiceRepository;
    private final FileRepository fileRepository;
    private final ReportMapper mapper;

    @Override
    public ReportDto getPracticeReport(UUID practiceId) {
        var practice = practiceRepository.findById(practiceId)
                .orElseThrow(() -> new NotFoundException(PracticeEntity.class, practiceId));

        var report = practice.getReport();

        if (report == null) {
            throw new NotFoundException(String.format("Отсутствует отчет по практике с id %s", practiceId));
        }

        return mapper.toDto(report);
    }

    @Override
    @Transactional
    public ReportDto setGrade(ReportId reportId, Integer grade) {
        var report = reportRepository.findById(reportId.reportId())
                .orElseThrow(() -> new NotFoundException(ReportEntity.class, reportId.reportId()));

        report.setGrade(grade);
        var savedReport = reportRepository.save(report);

        return mapper.toDto(savedReport);
    }

    @Override
    @Transactional
    public ReportDto attachFile(AuthUser user, ReportId reportId, UUID fileId) {
        var report = reportRepository.findById(reportId.reportId())
                .orElseThrow(() -> new NotFoundException(ReportEntity.class, reportId.reportId()));

        var file = fileRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException(FileEntity.class, fileId));

        if (!isUserAuthor(user, file.getUserId())) {
            throw new ForbiddenException("Пользователь не прикреплял данный файл");
        }

        report.setFileId(fileId);
        var savedReport = reportRepository.save(report);

        return mapper.toDto(savedReport);
    }

    @Override
    @Transactional
    public ReportDto unAttachFile(ReportId reportId) {
        var report = reportRepository.findById(reportId.reportId())
                .orElseThrow(() -> new NotFoundException(ReportEntity.class, reportId.reportId()));

        report.setFileId(null);
        var savedReport = reportRepository.save(report);

        return mapper.toDto(savedReport);
    }

    private Boolean isUserAuthor(AuthUser authUser, UUID authorId) {
        return authorId.equals(authUser.id());
    }
}
