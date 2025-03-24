package ru.hits.internship.language.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.hits.internship.language.entity.LanguageEntity;

public class LanguageSpecification {
    public static Specification<LanguageEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction(); // считает все значения, как equal
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }
}
