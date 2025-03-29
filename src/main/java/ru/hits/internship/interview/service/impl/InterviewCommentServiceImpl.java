package ru.hits.internship.interview.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.interview.entity.InterviewCommentEntity;
import ru.hits.internship.interview.mapper.InterviewCommentMapper;
import ru.hits.internship.interview.models.CreateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;
import ru.hits.internship.interview.models.UpdateInterviewCommentDto;
import ru.hits.internship.interview.repository.InterviewCommentRepository;
import ru.hits.internship.interview.service.InterviewCommentService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewCommentServiceImpl implements InterviewCommentService {

    private final InterviewCommentRepository repository;
    private final InterviewCommentMapper mapper;

    @Override
    @Transactional
    public InterviewCommentDto createComment(UUID authorId, UUID interviewId, CreateInterviewCommentDto createInterviewCommentDto) {
        return mapper.map(repository.save(mapper.map(authorId, interviewId, createInterviewCommentDto)));
    }

    @Override
    @Transactional
    public InterviewCommentDto updateComment(UUID commentId, UpdateInterviewCommentDto updateInterviewCommentDto) {
        InterviewCommentEntity comment = repository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id %s не найден".formatted(commentId)));

        mapper.update(comment, updateInterviewCommentDto);
        InterviewCommentEntity savedComment = repository.save(comment);

        return mapper.map(savedComment);
    }

    @Override
    @Transactional
    public void deleteComment(UUID commentId) {
        InterviewCommentEntity comment = repository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id %s не найден".formatted(commentId)));

        repository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedListDto<InterviewCommentDto> getCommentList(UUID interviewId, Pageable pageable) {
        return new PagedListDto<>(repository.findAllByInterviewId(interviewId, pageable).map(mapper::map));
    }
}
