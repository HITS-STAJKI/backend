package ru.hits.internship.user.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.hits.internship.user.model.entity.role.RoleEntity;

public class RoleSpecification {

    private RoleSpecification() {}

    public static <R extends RoleEntity> Specification<R> likeFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) return null;

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("user").get("fullName")),
                        "%" + fullName.toLowerCase() + "%"
                );
    }
}
