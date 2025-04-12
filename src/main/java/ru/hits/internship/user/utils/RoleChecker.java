package ru.hits.internship.user.utils;

import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.AuthUser;

public class RoleChecker {
    public static Boolean isUserHasRole(AuthUser user, UserRole userRole) {
        return user.roles().stream().anyMatch((role -> role.userRole().equals(userRole)));
    }
}