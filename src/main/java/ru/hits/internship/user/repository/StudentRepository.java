package ru.hits.internship.user.repository;

import ru.hits.internship.user.model.entity.role.StudentEntity;
import java.util.UUID;

public interface StudentRepository extends RoleRepository<StudentEntity, UUID> {}
