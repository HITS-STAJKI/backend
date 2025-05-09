package ru.hits.internship.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hits.internship.chat.entity.ChatReadStateEntity;
import ru.hits.internship.chat.model.chat.ChatInfoDto;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatReadStateRepository extends JpaRepository<ChatReadStateEntity, UUID> {
    Optional<ChatReadStateEntity> findByChatIdAndUserId(UUID chatId, UUID userId);
}
