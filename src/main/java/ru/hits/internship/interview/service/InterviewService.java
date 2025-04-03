package ru.hits.internship.interview.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.UpdateInterviewDto;

import java.util.UUID;

public interface InterviewService {

    InterviewDto createInterview(UUID studentId, CreateInterviewDto createInterviewDto);

    InterviewDto updateInterview(UUID interviewId, UpdateInterviewDto updateInterviewDto);

    void deleteInterview(UUID interviewId);

    InterviewDto getInterview(UUID interviewId);

    PagedListDto<InterviewDto> getInterviewList(UUID studentId, Pageable pageable);
}
