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
public class StudentInGroupFilter implements Filter<StudentEntity, StudentFilter> {
    @Override
    public boolean isApplicable(StudentFilter filter) {
        return filter.groupIds() != null && !filter.groupIds().isEmpty();
    }

    @Override
    public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, StudentFilter filter) {
        return root.get("group").get("id").in(filter.groupIds());
    }
}
