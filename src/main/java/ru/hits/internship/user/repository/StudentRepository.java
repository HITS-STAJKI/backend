package ru.hits.internship.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.user.model.entity.role.StudentEntity;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {}
