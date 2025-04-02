package ru.hits.internship.report.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.report.entity.ReportCommentEntity;

import java.util.UUID;

public interface ReportCommentRepository extends JpaRepository<ReportCommentEntity, UUID> {
    Page<ReportCommentEntity> findAllByReportId(UUID reportId, Pageable pageable);
}
