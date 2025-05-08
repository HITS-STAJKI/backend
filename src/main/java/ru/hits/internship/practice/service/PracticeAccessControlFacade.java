package ru.hits.internship.practice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.practice.repository.PracticeRepository;
import ru.hits.internship.report.entity.ReportEntity;

import java.util.UUID;

@Service("practiceAccessFacade")
@RequiredArgsConstructor
public class PracticeAccessControlFacade {
    private final PracticeRepository practiceRepository;

    public boolean isOwner(UUID userId, UUID practiceId) {
        var practice = practiceRepository.findById(practiceId)
                .orElseThrow(() -> new NotFoundException(ReportEntity.class, practiceId));
        UUID authorId = practice.getStudent().getUser().getId();

        return authorId.equals(userId);
    }
}
