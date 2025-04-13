package ru.hits.internship.group.filter.groupfilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.group.dto.GroupFilter;
import ru.hits.internship.group.entity.GroupEntity;

@Component("groupFilterNumber")
public class NumberFilter implements Filter<GroupEntity, GroupFilter> {

    @Override
    public boolean isApplicable(GroupFilter filter) {
        return filter.getNumber() != null && !filter.getNumber().isBlank();
    }

    @Override
    public Predicate toPredicate(Root<GroupEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
                                 GroupFilter filter) {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("number")),
                "%" + filter.getNumber().toLowerCase() + "%");
    }
}
