package ru.hits.internship.chat.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.hits.internship.user.model.entity.UserEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "messages", indexes = {
        @Index(name = "idx_message_chat_id_is_read", columnList = "chat_id, is_read"),
        @Index(name = "idx_message_sender_id", columnList = "sender_id")
})
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "content", nullable = false)
    private String content;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatEntity chat;
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;
}
