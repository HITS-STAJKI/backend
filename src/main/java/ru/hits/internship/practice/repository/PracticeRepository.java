package ru.hits.internship.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.practice.entity.PracticeEntity;

import java.util.UUID;

public interface PracticeRepository extends JpaRepository<PracticeEntity, UUID> {
}
