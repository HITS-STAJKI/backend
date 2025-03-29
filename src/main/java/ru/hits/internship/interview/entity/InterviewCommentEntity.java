package ru.hits.internship.interview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.hits.internship.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "interview_comments")
public class InterviewCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;
    @Column(name = "content", nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;
    @ManyToOne
    @JoinColumn(name = "interview_id", nullable = false)
    private InterviewEntity interview;
}
