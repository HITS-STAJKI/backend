package ru.hits.internship.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.internship.group.entity.GroupEntity;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {
    boolean existsByNumberIgnoreCase(String number);
}
