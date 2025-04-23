package ru.hits.internship.user.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hits.internship.user.model.entity.role.StudentEntity;

import java.util.UUID;

public interface StudentRepository extends RoleRepository<StudentEntity, UUID> {
    @Modifying
    @Query("UPDATE StudentEntity s SET s.isGraduated = true WHERE s.group.id = :groupId AND s.isGraduated = false")
    int graduateStudentsByGroup(@Param("groupId") UUID groupId);
}
