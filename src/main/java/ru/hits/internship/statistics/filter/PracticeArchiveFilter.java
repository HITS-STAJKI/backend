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
import java.util.Objects;

@Component("statsPracticeArchiveFilter")
public class PracticeArchiveFilter implements Filter<StudentEntity, StudentFilter> {

    @Override
    public boolean isApplicable(StudentFilter filter) {
        return Objects.isNull(filter.includeArchived()) || !filter.includeArchived();
    }

    @Override
    public Predicate toPredicate(Root<StudentEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb,
                                 StudentFilter filter) {
        query.distinct(true);

        Join<StudentEntity, PracticeEntity> practice = root.join("practices", JoinType.LEFT);

        return cb.or(
                cb.isFalse(practice.get("isArchived")),
                cb.isNull(practice.get("id"))
        );
    }
}
