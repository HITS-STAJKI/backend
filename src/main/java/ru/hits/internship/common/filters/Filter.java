package ru.hits.internship.common.filters;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

@FunctionalInterface
public interface Filter<E, F> {
    default boolean isApplicable(F filter) {
        return true;
    }

    Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, F filter);

    default Specification<E> build(F filter) {
        if (!isApplicable(filter)) {
            return null;
        }

        return (root, query, criteriaBuilder) -> toPredicate(root, query, criteriaBuilder, filter);
    }
}


