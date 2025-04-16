package ru.hits.internship.interview.service.common;

import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.Optional;
import java.util.UUID;

public class InterviewUtils {

    public static Optional<UUID> getStudentIdIfExists(AuthUser user) {
        return Optional.of(
                user.roles()
                        .get(UserRole.STUDENT)
                        .id()
        );
    }

    public static boolean isUserAuthor(AuthUser user, InterviewEntity interview) {
        Optional<UUID> studentId = getStudentIdIfExists(user);
        return studentId.isPresent() && interview.getStudent().getId().equals(studentId.get());
    }
}
