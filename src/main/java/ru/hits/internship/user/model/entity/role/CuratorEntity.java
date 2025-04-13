package ru.hits.internship.user.model.entity.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.user.model.common.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "curators")
public class CuratorEntity extends RoleEntity {
    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private CompanyPartnerEntity companyPartner;

    @Override
    public UserRole getUserRole() {
        return UserRole.CURATOR;
    }
}
