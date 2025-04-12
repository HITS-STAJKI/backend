package ru.hits.internship.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.user.model.entity.role.TeacherEntity;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<TeacherEntity, UUID> {}
