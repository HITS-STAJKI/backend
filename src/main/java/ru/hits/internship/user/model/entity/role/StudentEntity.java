package ru.hits.internship.user.model.entity.role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hits.internship.chat.entity.ChatEntity;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.user.model.common.UserRole;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class StudentEntity extends RoleEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", nullable = true)
    private GroupEntity group;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private ChatEntity chat;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<InterviewEntity> interviews;

    @Column(name = "is_academ", nullable = false, columnDefinition = "boolean default false")
    private Boolean isAcadem = false;

    @Column(name = "is_graduated", nullable = false, columnDefinition = "boolean default false")
    private Boolean isGraduated = false;

    @Override
    public UserRole getUserRole() {
        return UserRole.STUDENT;
    }
}
