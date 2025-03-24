package ru.hits.internship.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.practice.mapper.PracticeMapper;
import ru.hits.internship.practice.models.CreatePracticeDto;
import ru.hits.internship.practice.models.PracticeDto;
import ru.hits.internship.practice.models.UpdatePracticeDto;
import ru.hits.internship.practice.repository.PracticeRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PracticeServiceImpl implements PracticeService {
    private final PracticeRepository repository;
    private final PracticeMapper mapper;

    @Override
    public PracticeDto getStudentCurrentPractice(UUID studentId) {
        var practice = repository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(String.format("У студента с id: %s отсутствует практика", studentId)));

        return mapper.toDto(practice);
    }

    @Override
    public PagedListDto<PracticeDto> getStudentPractices(UUID studentId, Pageable pageable) {
        var practice = repository.findById(studentId, pageable);

        return null;
    }

    @Override
    public PracticeDto createStudentPractice(CreatePracticeDto createPracticeDto) {
        return null;
    }

    @Override
    public PracticeDto approveStudentPractice(UUID practiceId) {
        return null;
    }

    @Override
    public PagedListDto<PracticeDto> getPracticeRequests(Pageable pageable) {
        return null;
    }

    @Override
    public PracticeDto updatePractice(UUID practiceId, UpdatePracticeDto updatePracticeDto) {
        return null;
    }

    @Override
    public PracticeDto archiveStudentPractice(UUID practiceId) {
        return null;
    }
}
