package ru.hits.internship.interview.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.interview.models.CreateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;
import ru.hits.internship.interview.models.UpdateInterviewCommentDto;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

public interface InterviewCommentService {

    InterviewCommentDto createComment(
            AuthUser user,
            UUID interviewId,
            CreateInterviewCommentDto createInterviewCommentDto
    );

    InterviewCommentDto updateComment(
            AuthUser user,
            UUID commentId,
            UpdateInterviewCommentDto updateInterviewCommentDto
    );

    void deleteComment(AuthUser user, UUID commentId);

    PagedListDto<InterviewCommentDto> getCommentList(AuthUser user, UUID interviewId, Pageable pageable);
}
