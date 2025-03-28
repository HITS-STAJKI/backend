package ru.hits.internship.interview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.internship.interview.entity.InterviewEntity;

import java.util.UUID;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, UUID> {

    Page<InterviewEntity> findAllByStudentId(UUID studentId, Pageable pageable);
}
