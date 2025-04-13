package ru.hits.internship.partner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import java.util.UUID;

@Repository
public interface CompanyPartnerRepository
        extends JpaRepository<CompanyPartnerEntity, UUID>, JpaSpecificationExecutor<CompanyPartnerEntity> {
    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, UUID id);
}
