package ru.hits.internship.interview.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.models.InterviewFilter;

@Component("interviewFilterLanguageIds")
public class LanguageIdsFilter implements Filter<InterviewEntity, InterviewFilter> {

    @Override
    public boolean isApplicable(InterviewFilter filter) {
        return filter.getLanguageIds() != null && !filter.getLanguageIds().isEmpty();
    }

    @Override
    public Predicate toPredicate(Root<InterviewEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, InterviewFilter filter) {
        var subquery = query.subquery(Long.class);
        var subRoot = subquery.from(InterviewEntity.class);
        var languagesJoin = subRoot.join("languages");

        subquery.select(criteriaBuilder.count(languagesJoin.get("id")))
                .where(
                        criteriaBuilder.equal(subRoot.get("id"), root.get("id")),
                        languagesJoin.get("id").in(filter.getLanguageIds())
                );

        return criteriaBuilder.equal(subquery, filter.getLanguageIds().size());
    }
}
