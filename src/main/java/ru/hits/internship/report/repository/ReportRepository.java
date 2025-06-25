package ru.hits.internship.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.report.entity.ReportEntity;
import ru.hits.internship.user.model.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<ReportEntity, UUID> {
    Boolean existsByAuthorAndId(UserEntity author, UUID id);

    Optional<ReportEntity> findByFileId(UUID fileId);
}
