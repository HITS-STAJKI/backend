package ru.hits.internship.chat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hits.internship.chat.entity.MessageEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {


    @EntityGraph(attributePaths = {"sender"})
    Page<MessageEntity> findAllByChat_Id(UUID chatId, Pageable pageable);

    @Query("""
            select case when count(m) > 0 then true else false end
            from MessageEntity m
            where m.id = :messageId
              and m.sender.id = :userId
            """)
    boolean isOwner(UUID messageId, UUID userId);


    @Query("""
            select count(m)
                from MessageEntity m
              where m.chat.id = :chatId
                  and m.createdAt > :since
                  and m.sender.id <> :userId
            """)
    long countUnread(UUID chatId, UUID userId, LocalDateTime since);


    @Query("""
            select count(m)
                from MessageEntity m
              where m.chat.id = :chatId
                  and m.sender.id <> :senderId
        """)
    long countAllByChat_IdAndSender_IdNot(UUID chatId, UUID senderId);
}
