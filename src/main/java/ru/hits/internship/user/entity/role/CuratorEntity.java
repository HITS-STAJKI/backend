package ru.hits.internship.user.entity.role;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;

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
}
