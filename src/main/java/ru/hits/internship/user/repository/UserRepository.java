package ru.hits.internship.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.user.model.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
    Page<UserEntity> findAllByIdNot(UUID excludedId, Pageable pageable);

    default UserEntity findByIdOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException(UserEntity.class, id));
    }
}
