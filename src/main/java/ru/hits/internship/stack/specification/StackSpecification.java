package ru.hits.internship.stack.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.hits.internship.stack.entity.StackEntity;

public class StackSpecification {
    public static Specification<StackEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }
}
