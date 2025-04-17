package ru.hits.internship.interview.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.models.InterviewFilter;

@Component("interviewFilterStatus")
public class StatusFilter implements Filter<InterviewEntity, InterviewFilter> {

    @Override
    public boolean isApplicable(InterviewFilter filter) {
        return filter.getStatus() != null;
    }

    @Override
    public Predicate toPredicate(Root<InterviewEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, InterviewFilter filter) {
        return criteriaBuilder.equal(root.get("status"), filter.getStatus());
    }
}
