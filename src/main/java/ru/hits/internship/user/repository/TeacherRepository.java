package ru.hits.internship.user.repository;

import ru.hits.internship.user.model.entity.role.TeacherEntity;

import java.util.UUID;

public interface TeacherRepository extends RoleRepository<TeacherEntity, UUID> {}
