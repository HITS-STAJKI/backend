package ru.hits.internship.user.entity.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.hits.internship.user.models.role.UserRole;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teachers")
public class TeacherEntity extends RoleEntity {

    @Override
    public UserRole getUserRole() {
        return UserRole.TEACHER;
    }
}
