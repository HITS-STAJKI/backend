package ru.hits.internship.chat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hits.internship.chat.entity.MessageEntity;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

    @Modifying(clearAutomatically = true)
    @Query("""
            update MessageEntity m
                     set m.isRead = true
                     where m.chat.id = :chatId
                       and m.sender.id <> :userId
                       and m.isRead = false
            """)
    void markAsRead(UUID chatId, UUID userId);

    @EntityGraph(attributePaths = {"sender"})
    Page<MessageEntity> findAllByChat_Id(UUID chatId, Pageable pageable);

    @Query("""
            select case when count(m) > 0 then true else false end
            from MessageEntity m
            where m.id = :messageId
              and m.sender.id = :userId
            """)
    boolean isOwner(UUID messageId, UUID userId);
}
