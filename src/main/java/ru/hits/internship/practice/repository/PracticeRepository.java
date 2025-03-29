package ru.hits.internship.practice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.practice.entity.PracticeEntity;

import java.util.UUID;

public interface PracticeRepository extends JpaRepository<PracticeEntity, UUID> {
    Page<PracticeEntity> findAllByStudentId(UUID studentId, Pageable pageable);

    Page<PracticeEntity> findAllByIsApprovedFalse(Pageable pageable);
}
