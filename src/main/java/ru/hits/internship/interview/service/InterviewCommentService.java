package ru.hits.internship.interview.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.interview.models.CreateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;
import ru.hits.internship.interview.models.UpdateInterviewCommentDto;
import ru.hits.internship.common.models.pagination.PagedListDto;

import java.util.UUID;

public interface InterviewCommentService {

    InterviewCommentDto createComment(
            UUID authorId,
            UUID interviewId,
            CreateInterviewCommentDto createInterviewCommentDto
    );

    InterviewCommentDto updateComment(
            UUID commentId,
            UpdateInterviewCommentDto updateInterviewCommentDto
    );

    void deleteComment(UUID commentId);

    PagedListDto<InterviewCommentDto> getCommentList(UUID interviewId, Pageable pageable);
}
