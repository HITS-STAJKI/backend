package ru.hits.internship.interview.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.InterviewFilter;
import ru.hits.internship.interview.models.UpdateInterviewDto;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

public interface InterviewService {

    InterviewDto createInterview(AuthUser user, CreateInterviewDto createInterviewDto);

    InterviewDto updateInterview(AuthUser user, UUID interviewId, UpdateInterviewDto updateInterviewDto);

    void deleteInterview(AuthUser user, UUID interviewId);

    InterviewDto getInterview(AuthUser user, UUID interviewId);

    PagedListDto<InterviewDto> getInterviewList(AuthUser user, InterviewFilter interviewFilter, Pageable pageable);

    PagedListDto<InterviewDto> getInterviewList(AuthUser user, Pageable pageable);
}
