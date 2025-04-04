package ru.hits.internship.interview.service.common;

import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.response.RoleDto;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.Optional;
import java.util.UUID;

public class InterviewUtils {

    public static Optional<UUID> getStudentIdIfExists(AuthUser user) {
        return user.roles()
                .stream()
                .filter(role -> role.userRole() == UserRole.STUDENT)
                .map(RoleDto::id)
                .findFirst();
    }

    public static boolean isUserAuthor(AuthUser user, InterviewEntity interview) {
        Optional<UUID> studentId = getStudentIdIfExists(user);
        return studentId.isPresent() && interview.getStudent().getId().equals(studentId.get());
    }
}
