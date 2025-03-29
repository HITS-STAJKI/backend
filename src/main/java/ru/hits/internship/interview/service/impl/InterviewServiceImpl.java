package ru.hits.internship.interview.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.mapper.InterviewMapper;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.UpdateInterviewDto;
import ru.hits.internship.interview.repository.InterviewRepository;
import ru.hits.internship.interview.service.InterviewService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository repository;
    private final InterviewMapper mapper;

    @Override
    @Transactional
    public InterviewDto createInterview(UUID studentId, CreateInterviewDto createInterviewDto) {
        return mapper.map(repository.save(mapper.map(studentId, createInterviewDto)));
    }

    @Override
    @Transactional
    public InterviewDto updateInterview(UUID interviewId, UpdateInterviewDto updateInterviewDto) {
        InterviewEntity interview = repository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Интервью с id %s не найдено".formatted(interviewId)));

        mapper.update(interview, updateInterviewDto);
        InterviewEntity savedInterview = repository.save(interview);

        return mapper.map(savedInterview);
    }

    @Override
    @Transactional
    public void deleteInterview(UUID interviewId) {
        InterviewEntity interview = repository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Интервью с id %s не найдено".formatted(interviewId)));

        repository.delete(interview);
    }

    @Override
    @Transactional(readOnly = true)
    public InterviewDto getInterview(UUID interviewId) {
        return repository.findById(interviewId)
                .map(mapper::map)
                .orElseThrow(() -> new NotFoundException("Интервью с id %s не найдено".formatted(interviewId)));
    }

    @Override
    @Transactional(readOnly = true)
    public PagedListDto<InterviewDto> getInterviewList(UUID studentId, Pageable pageable) {
        return new PagedListDto<>(repository.findAllByStudentId(studentId, pageable).map(mapper::map));
    }
}
