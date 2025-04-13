package ru.hits.internship.group.filter.groupfilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.group.dto.GroupFilter;
import ru.hits.internship.group.entity.GroupEntity;

@Component("groupFilterId")
public class IdFilter implements Filter<GroupEntity, GroupFilter> {

    @Override
    public boolean isApplicable(GroupFilter filter) {
        return filter.getId() != null;
    }

    @Override
    public Predicate toPredicate(Root<GroupEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
                                 GroupFilter filter) {
        return criteriaBuilder.equal(root.get("id"), filter.getId());
    }
}
