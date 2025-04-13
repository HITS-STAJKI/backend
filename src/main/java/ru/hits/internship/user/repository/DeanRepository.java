package ru.hits.internship.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.user.model.entity.role.DeanEntity;

import java.util.UUID;

public interface DeanRepository extends JpaRepository<DeanEntity, UUID> {
    Page<DeanEntity> findAllByUserIdNot(UUID excludedUserId, Pageable pageable);
}
