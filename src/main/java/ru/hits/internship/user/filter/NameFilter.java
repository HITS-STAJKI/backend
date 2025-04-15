package ru.hits.internship.user.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.user.model.dto.user.UserFilter;
import ru.hits.internship.user.model.entity.UserEntity;

@Component("userFilterName")
public class NameFilter implements Filter<UserEntity, UserFilter> {

    @Override
    public boolean isApplicable(UserFilter filter) {
        return filter.getFullName() != null && !filter.getFullName().isBlank();
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
                                 UserFilter filter) {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")),
                "%" + filter.getFullName().toLowerCase() + "%");
    }
}
