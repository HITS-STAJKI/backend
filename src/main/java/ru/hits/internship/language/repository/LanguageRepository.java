package ru.hits.internship.language.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.hits.internship.language.entity.LanguageEntity;

import java.util.UUID;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, UUID>, JpaSpecificationExecutor<LanguageEntity> {
    Boolean existsByName(String name);
}
