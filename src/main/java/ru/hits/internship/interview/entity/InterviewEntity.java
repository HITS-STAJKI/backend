package ru.hits.internship.interview.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.hits.internship.interview.models.StatusEnum;
import ru.hits.internship.language.entity.LanguageEntity;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.stack.entity.StackEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "interviews")
public class InterviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "stack_id", nullable = false)
    private StackEntity stack;
    @ManyToMany
    private List<LanguageEntity> languages;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyPartnerEntity company;
    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL)
    private List<InterviewCommentEntity> comments;
}
