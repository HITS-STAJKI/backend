package ru.hits.internship.user.filter;

import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.UserFilter;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.*;

@Component("userRoleFilter")
public class UserRoleFilter implements Filter<UserEntity, UserFilter> {

    @Override
    public boolean isApplicable(UserFilter filter) {
        return filter.getUserRole() != null;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, UserFilter filter) {
        Join<UserEntity, RoleEntity> rolesJoin = root.join("roles");
        UserRole specifiedRole = filter.getUserRole();

        Class<? extends RoleEntity> specifiedRoleType = switch (specifiedRole) {
            case STUDENT -> StudentEntity.class;
            case DEAN -> DeanEntity.class;
            case CURATOR -> CuratorEntity.class;
            case TEACHER -> TeacherEntity.class;
            case EDUCATIONAL_PROGRAM_LEAD -> EducationalProgramLeadEntity.class;
            default -> throw new BadRequestException("Unknown role: " + specifiedRole);
        };

        return criteriaBuilder.equal(rolesJoin.type(), specifiedRoleType);
    }
}