package ru.hits.internship.partner.filter.partnerfilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.partner.models.PartnerFilter;
import ru.hits.internship.practice.entity.PracticeEntity;

@Component("partnerFilterIsNew")
public class IsNewFilter implements Filter<CompanyPartnerEntity, PartnerFilter> {

    @Override
    public boolean isApplicable(PartnerFilter filter) {
        return filter.getIsNew() != null && Boolean.TRUE.equals(filter.getIsNew());
    }

    @Override
    public Predicate toPredicate(Root<CompanyPartnerEntity> root, CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder, PartnerFilter filter) {
        Join<CompanyPartnerEntity, PracticeEntity> practiceJoin = root.join("practices", JoinType.LEFT);

        return criteriaBuilder.isNull(practiceJoin.get("id"));
    }
}
