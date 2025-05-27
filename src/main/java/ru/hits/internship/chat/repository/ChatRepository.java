package ru.hits.internship.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hits.internship.chat.entity.ChatEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, UUID> {
    List<ChatEntity> findAllByStudent_IdIn(Set<UUID> studentIds);
    Optional<ChatEntity> findByStudent_Id(UUID studentId);
    boolean existsByStudent_Id(UUID studentId);

    @Query("""
            select case when count(c) > 0 then true else false end
            from ChatEntity c
            where c.id = :chatId
              and c.student.id = :studentId
            """)
    boolean isChatOfStudent(UUID chatId, UUID studentId);
}
