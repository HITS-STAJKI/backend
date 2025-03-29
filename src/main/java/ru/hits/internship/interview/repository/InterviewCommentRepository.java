package ru.hits.internship.interview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.internship.interview.entity.InterviewCommentEntity;

import java.util.UUID;

@Repository
public interface InterviewCommentRepository extends JpaRepository<InterviewCommentEntity, UUID> {

    Page<InterviewCommentEntity> findAllByInterviewId(UUID interviewId, Pageable pageable);
}
