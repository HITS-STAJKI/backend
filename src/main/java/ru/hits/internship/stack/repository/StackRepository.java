package ru.hits.internship.stack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.internship.stack.entity.StackEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface StackRepository extends JpaRepository<StackEntity, UUID> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);

    List<StackEntity> findAllByName(String name);
}
