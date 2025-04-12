package ru.hits.internship.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.user.model.entity.role.CuratorEntity;

import java.util.UUID;

public interface CuratorRepository extends JpaRepository<CuratorEntity, UUID> {}
