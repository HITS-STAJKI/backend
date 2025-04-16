package ru.hits.internship.partner.filter.partnerfilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.partner.models.PartnerFilter;

@Component("partnerFilterName")
public class NameFilter implements Filter<CompanyPartnerEntity, PartnerFilter> {

    @Override
    public boolean isApplicable(PartnerFilter filter) {
        return filter.getName() != null && !filter.getName().isBlank();
    }

    @Override
    public Predicate toPredicate(Root<CompanyPartnerEntity> root, CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder, PartnerFilter filter) {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                "%" + filter.getName().toLowerCase() + "%");
    }
}
