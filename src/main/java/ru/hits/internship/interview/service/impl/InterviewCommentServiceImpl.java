package ru.hits.internship.interview.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.interview.entity.InterviewCommentEntity;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.mapper.InterviewCommentMapper;
import ru.hits.internship.interview.models.CreateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;
import ru.hits.internship.interview.models.UpdateInterviewCommentDto;
import ru.hits.internship.interview.repository.InterviewCommentRepository;
import ru.hits.internship.interview.repository.InterviewRepository;
import ru.hits.internship.interview.service.InterviewCommentService;
import ru.hits.internship.user.entity.UserEntity;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewCommentServiceImpl implements InterviewCommentService {

    private final InterviewCommentRepository commentRepository;
    private final InterviewCommentMapper commentMapper;
    //TODO Прикрутить репозиторий (feature/#3932?)
    //private final UserRepository userRepository;
    private final InterviewRepository interviewRepository;

    @Override
    @Transactional
    public InterviewCommentDto createComment(UUID authorId, UUID interviewId, CreateInterviewCommentDto createInterviewCommentDto) {
        InterviewCommentEntity entity = commentMapper.map(createInterviewCommentDto);

        //if (!userRepository.existsById(authorId)) {
        //    throw new NotFoundException("Пользователь с id %s не найден".formatted(authorId));
        //}
        //UserEntity author = userRepository.getReferenceById(authorId);
        //entity.setAuthor(author);

        if (!interviewRepository.existsById(interviewId)) {
            throw new NotFoundException("Отбор с id %s не найден".formatted(interviewId));
        }
        InterviewEntity interview = interviewRepository.getReferenceById(interviewId);
        entity.setInterview(interview);

        InterviewCommentEntity savedEntity = commentRepository.save(entity);
        return commentMapper.map(savedEntity);
    }

    @Override
    @Transactional
    public InterviewCommentDto updateComment(UUID commentId, UpdateInterviewCommentDto updateInterviewCommentDto) {
        InterviewCommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id %s не найден".formatted(commentId)));

        commentMapper.update(comment, updateInterviewCommentDto);
        InterviewCommentEntity savedComment = commentRepository.save(comment);

        return commentMapper.map(savedComment);
    }

    @Override
    @Transactional
    public void deleteComment(UUID commentId) {
        InterviewCommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id %s не найден".formatted(commentId)));

        commentRepository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedListDto<InterviewCommentDto> getCommentList(UUID interviewId, Pageable pageable) {
        Page<InterviewCommentEntity> page = commentRepository.findAllByInterviewId(interviewId, pageable);

        return new PagedListDto<>(page.map(commentMapper::map));
    }
}
