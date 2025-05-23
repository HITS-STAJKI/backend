package ru.hits.internship.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hits.internship.user.model.entity.role.StudentEntity;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface StudentRepository extends RoleRepository<StudentEntity, UUID> {
    @Modifying
    @Query("UPDATE StudentEntity s SET s.isGraduated = true WHERE s.group.id = :groupId AND s.isGraduated = false")
    int graduateStudentsByGroup(@Param("groupId") UUID groupId);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM StudentEntity s WHERE s.user.id = :userId")
    boolean existsByUserId(@Param("userId") UUID userId);

    Optional<StudentEntity> findByUser_Id(UUID userId);

    Page<StudentEntity> findAllByIdIn(Set<UUID> userIds, Pageable pageable);
}
