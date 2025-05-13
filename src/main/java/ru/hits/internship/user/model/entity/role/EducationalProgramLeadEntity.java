package ru.hits.internship.user.model.entity.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hits.internship.user.model.common.UserRole;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "educational_program_leads")
public class EducationalProgramLeadEntity extends RoleEntity {

    @Override
    public UserRole getUserRole() {
        return UserRole.EDUCATIONAL_PROGRAM_LEAD;
    }
}
