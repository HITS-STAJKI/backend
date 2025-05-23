package ru.hits.internship.practice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.report.entity.ReportEntity;
import ru.hits.internship.stack.entity.StackEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "practices")
public class PracticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid = false;
    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved = false;
    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived = false;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "report_id")
    private ReportEntity report;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyPartnerEntity company;
    @ManyToOne
    @JoinColumn(name = "stack_id", nullable = false)
    private StackEntity stack;

    public PracticeEntity(StudentEntity student, CompanyPartnerEntity company, StackEntity stack, Boolean isPaid) {
        this.student = student;
        this.company = company;
        this.stack = stack;
        this.isPaid = isPaid;
    }
}
