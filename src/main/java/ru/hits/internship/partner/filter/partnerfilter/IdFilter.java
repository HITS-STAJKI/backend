package ru.hits.internship.partner.filter.partnerfilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.partner.models.PartnerFilter;

@Component("partnerFilterId")
public class IdFilter implements Filter<CompanyPartnerEntity, PartnerFilter> {

    @Override
    public boolean isApplicable(PartnerFilter filter) {
        return filter.getId() != null;
    }

    @Override
    public Predicate toPredicate(Root<CompanyPartnerEntity> root, CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder, PartnerFilter filter) {
        return criteriaBuilder.equal(root.get("id"), filter.getId());
    }
}
