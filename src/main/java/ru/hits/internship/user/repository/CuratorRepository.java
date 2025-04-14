package ru.hits.internship.user.repository;

import ru.hits.internship.user.model.entity.role.CuratorEntity;
import java.util.UUID;

public interface CuratorRepository extends RoleRepository<CuratorEntity, UUID> {}
