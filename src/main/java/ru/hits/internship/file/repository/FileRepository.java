package ru.hits.internship.file.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.internship.file.entity.FileEntity;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {
    boolean existsByIdAndUserId(UUID fileId, UUID userId);

    Page<FileEntity> findAllByUserId(UUID userId, Pageable pageable);
}
