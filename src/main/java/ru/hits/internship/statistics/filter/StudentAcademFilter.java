package ru.hits.internship.statistics.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.statistics.dto.StudentFilter;
import ru.hits.internship.user.model.entity.role.StudentEntity;

@Component
public class StudentAcademFilter implements Filter<StudentEntity, StudentFilter> {
    @Override
    public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, StudentFilter filter) {
        if (filter.isAcadem() == null) {
            return criteriaBuilder.conjunction();
        }

        return criteriaBuilder.equal(root.get("isAcadem"), filter.isAcadem());
    }
}