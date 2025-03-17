package ru.hits.internship.group.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hits.internship.user.entity.role.StudentEntity;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "groups")
public class GroupEntity {
    @Id
    private UUID id;
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<StudentEntity> students;
}
