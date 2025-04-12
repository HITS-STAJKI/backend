package ru.hits.internship.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.user.model.entity.role.DeanEntity;

import java.util.UUID;

public interface DeanRepository extends JpaRepository<DeanEntity, UUID> {}
