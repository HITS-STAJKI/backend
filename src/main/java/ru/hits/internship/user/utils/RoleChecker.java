package ru.hits.internship.user.utils;

import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.entity.UserEntity;

public class RoleChecker {

    private RoleChecker() {
    }

    public static Boolean isUserHasRole(AuthUser user, UserRole userRole) {
        return user.roles().containsKey(userRole);
    }

    public static void verifyRoleAvailable(UserEntity user, UserRole userRole) {
        if (user.getRoles().stream().anyMatch((role -> role.getUserRole().equals(userRole)))) {
            throw new BadRequestException("User is already has " + userRole + " role");
        }
    }

    public static boolean isUserEntityHasRole(UserEntity user, UserRole userRole) {
        return user.getRoles()
                .stream()
                .anyMatch(r -> r.getUserRole() == userRole);
    }
}