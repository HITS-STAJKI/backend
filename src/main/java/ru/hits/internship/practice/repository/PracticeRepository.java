package ru.hits.internship.practice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hits.internship.practice.entity.PracticeEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PracticeRepository extends JpaRepository<PracticeEntity, UUID>, JpaSpecificationExecutor<PracticeEntity> {
    Optional<PracticeEntity> findByStudentIdAndIsArchivedFalse(UUID studentId);

    List<PracticeEntity> findAllByCompanyIdAndIsApprovedFalse(UUID companyId);

    List<PracticeEntity> findAllByIdInAndIsApprovedFalse(List<UUID> practiceIds);

    Page<PracticeEntity> findAllByStudentId(UUID studentId, Pageable pageable);

    Page<PracticeEntity> findAllByIsApprovedFalse(Pageable pageable);

    @Modifying
    @Query("UPDATE PracticeEntity p SET p.isApproved = true WHERE p.student.group.id = :groupId AND p.isApproved = false")
    int approvePracticesByGroup(@Param("groupId") UUID groupId);

    @Modifying
    @Query("UPDATE PracticeEntity p SET p.isArchived = true WHERE p.student.group.id = :groupId AND p.isArchived = false")
    int archivePracticesByGroup(@Param("groupId") UUID groupId);

    @Query("SELECT p FROM PracticeEntity p WHERE p.student.id IN :studentIds AND p.isArchived = false")
    List<PracticeEntity> findByStudentIdsAndIsArchivedFalse(@Param("studentIds") List<UUID> studentIds);

}
