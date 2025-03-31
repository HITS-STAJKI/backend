package ru.hits.internship.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.user.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
