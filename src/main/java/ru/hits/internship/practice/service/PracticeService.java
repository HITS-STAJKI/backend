package ru.hits.internship.practice.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.practice.models.CreatePracticeDto;
import ru.hits.internship.practice.models.PracticeDto;
import ru.hits.internship.practice.models.UpdatePracticeDto;

import java.util.UUID;

public interface PracticeService {
    PracticeDto getStudentCurrentPractice(UUID studentId);

    PagedListDto<PracticeDto> getStudentPractices(UUID studentId, Pageable pageable);

    PracticeDto createStudentPractice(CreatePracticeDto createPracticeDto);

    PracticeDto approveStudentPractice(UUID practiceId);

    PagedListDto<PracticeDto> getPracticeRequests(Pageable pageable);

    PracticeDto updatePractice(UUID practiceId, UpdatePracticeDto updatePracticeDto);

    PracticeDto archiveStudentPractice(UUID practiceId);
}
