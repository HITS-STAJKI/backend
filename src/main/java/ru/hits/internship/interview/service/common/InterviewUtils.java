package ru.hits.internship.interview.service.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.repository.InterviewRepository;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.Optional;
import java.util.UUID;

@Service("interviewUtils")
@RequiredArgsConstructor
public class InterviewUtils {

    private final InterviewRepository interviewRepository;

    public static Optional<UUID> getStudentIdIfExists(AuthUser user) {
        return Optional.of(
                user.roles()
                        .get(UserRole.STUDENT)
                        .id()
        );
    }

    public boolean isUserAuthor(AuthUser user, UUID interviewId) {
        Optional<UUID> studentId = getStudentIdIfExists(user);
        InterviewEntity interview = interviewRepository.findById(interviewId).orElse(null);
        return interview != null && studentId.isPresent() && interview.getStudent().getId().equals(studentId.get());
    }
}
