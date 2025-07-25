package ru.hits.internship.user.filter.student;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.user.model.dto.role.filter.StudentFilter;
import ru.hits.internship.user.model.entity.role.StudentEntity;

import java.time.LocalDateTime;

@Component
public class StudentLastLoginFilter implements Filter<StudentEntity, StudentFilter> {
    @Override
    public boolean isApplicable(StudentFilter filter) {
        return filter.lastLogin() != null;
    }

    @Override
    public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, StudentFilter filter) {
        var lastLoginTime = root.get("user").get("lastLoginDate").as(LocalDateTime.class);

        return criteriaBuilder.greaterThanOrEqualTo(lastLoginTime, filter.lastLogin());
    }
}
