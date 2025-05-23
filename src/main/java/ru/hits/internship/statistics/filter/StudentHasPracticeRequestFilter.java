package ru.hits.internship.statistics.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.practice.entity.PracticeEntity;
import ru.hits.internship.statistics.dto.StudentFilter;
import ru.hits.internship.user.model.entity.role.StudentEntity;

@Component
public class StudentHasPracticeRequestFilter implements Filter<StudentEntity, StudentFilter> {
    @Override
    public boolean isApplicable(StudentFilter filter) {
        return filter.hasPracticeRequest() != null;
    }

    @Override
    public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, StudentFilter filter) {
        Join<PracticeEntity, StudentEntity> practiceJoin = root.join("practices", JoinType.LEFT);

        if (filter.hasPracticeRequest()) {
            return criteriaBuilder.and(
                    criteriaBuilder.isNotNull(practiceJoin.get("id")),
                    criteriaBuilder.isFalse(practiceJoin.get("isApproved"))
            );
        } else {
            return criteriaBuilder.isNull(practiceJoin.get("id"));
        }
    }
}
