package ru.hits.internship.interview.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.models.InterviewFilter;

@Component("interviewFilterUpdatedAt")
public class UpdatedAtFilter implements Filter<InterviewEntity, InterviewFilter> {

    @Override
    public boolean isApplicable(InterviewFilter filter) {
        return filter.getModifiedAtFrom() != null || filter.getModifiedAtTo() != null;
    }

    @Override
    public Predicate toPredicate(Root<InterviewEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, InterviewFilter filter) {
        return criteriaBuilder.and(
                filter.getModifiedAtFrom() != null ? criteriaBuilder.and(
                        criteriaBuilder.isNotNull(root.get("modifiedAt")),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("modifiedAt"), filter.getModifiedAtFrom())
                ): criteriaBuilder.conjunction(),
                filter.getModifiedAtTo() != null ? criteriaBuilder.and(
                        criteriaBuilder.isNotNull(root.get("modifiedAt")),
                        criteriaBuilder.lessThanOrEqualTo(root.get("modifiedAt"), filter.getModifiedAtTo())
                ): criteriaBuilder.conjunction()
        );
    }
}
