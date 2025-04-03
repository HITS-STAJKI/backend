package ru.hits.internship.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.report.entity.ReportEntity;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<ReportEntity, UUID> {
}
