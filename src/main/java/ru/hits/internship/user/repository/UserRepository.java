package ru.hits.internship.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.user.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
    Boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByEmailIn(Set<String> emails);

    default UserEntity findByIdOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException(UserEntity.class, id));
    }
}
