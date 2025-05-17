package ru.hits.internship.interview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.stack.entity.StackEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, UUID>, JpaSpecificationExecutor<InterviewEntity> {
    List<InterviewEntity> findAllByCompanyAndStudentAndStack(CompanyPartnerEntity company, StudentEntity student, StackEntity stack);

    Page<InterviewEntity> findAllByStudentId(UUID studentId, Pageable pageable);
}
