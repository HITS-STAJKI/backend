package ru.hits.internship.interview.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hits.internship.interview.models.StatusEnum;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.stack.entity.StackEntity;
import ru.hits.internship.user.entity.role.StudentEntity;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "stack_id", nullable = false)
    private StackEntity stack;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyPartnerEntity company;
    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL)
    private List<InterviewCommentEntity> comments;
}
