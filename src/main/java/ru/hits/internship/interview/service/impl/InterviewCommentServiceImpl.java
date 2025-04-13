package ru.hits.internship.interview.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.ForbiddenException;
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
import ru.hits.internship.interview.service.common.InterviewUtils;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

import static ru.hits.internship.user.utils.RoleChecker.isUserHasRole;

@Service
@RequiredArgsConstructor
public class InterviewCommentServiceImpl implements InterviewCommentService {

    private final InterviewCommentRepository commentRepository;
    private final InterviewCommentMapper commentMapper;
    private final UserRepository userRepository;
    private final InterviewRepository interviewRepository;

    @Override
    @Transactional
    public InterviewCommentDto createComment(AuthUser user, UUID interviewId, CreateInterviewCommentDto createInterviewCommentDto) {
        if (!isUserAuthor(user, interviewId) && !isUserHasRole(user, UserRole.DEAN) && !isUserHasRole(user, UserRole.CURATOR)) {
            throw new ForbiddenException();
        }

        InterviewCommentEntity entity = commentMapper.map(createInterviewCommentDto);

        if (!userRepository.existsById(user.id())) {
            throw new NotFoundException("Пользователь с id %s не найден".formatted(user.id()));
        }
        UserEntity author = userRepository.getReferenceById(user.id());
        entity.setAuthor(author);

        if (!interviewRepository.existsById(interviewId)) {
            throw new NotFoundException("Отбор с id %s не найден".formatted(interviewId));
        }
        InterviewEntity interview = interviewRepository.getReferenceById(interviewId);
        entity.setInterview(interview);

        InterviewCommentEntity savedEntity = commentRepository.saveAndFlush(entity);
        return commentMapper.map(savedEntity);
    }

    @Override
    @Transactional
    public InterviewCommentDto updateComment(AuthUser user, UUID commentId, UpdateInterviewCommentDto updateInterviewCommentDto) {
        InterviewCommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id %s не найден".formatted(commentId)));

        if (!isUserAuthor(user, comment)) {
            throw new ForbiddenException();
        }

        commentMapper.update(comment, updateInterviewCommentDto);
        InterviewCommentEntity savedComment = commentRepository.saveAndFlush(comment);

        return commentMapper.map(savedComment);
    }

    @Override
    @Transactional
    public void deleteComment(AuthUser user, UUID commentId) {
        InterviewCommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id %s не найден".formatted(commentId)));

        if (!isUserAuthor(user, comment)) {
            throw new ForbiddenException();
        }

        commentRepository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedListDto<InterviewCommentDto> getCommentList(AuthUser user, UUID interviewId, Pageable pageable) {
        if (!isUserAuthor(user, interviewId) && !isUserHasRole(user, UserRole.DEAN) && !isUserHasRole(user, UserRole.CURATOR)) {
            throw new ForbiddenException();
        }

        Page<InterviewCommentEntity> page = commentRepository.findAllByInterviewId(interviewId, pageable);

        return new PagedListDto<>(page.map(commentMapper::map));
    }

    private static boolean isUserAuthor(AuthUser user, InterviewCommentEntity comment) {
        return comment.getAuthor().getId().equals(user.id());
    }

    private boolean isUserAuthor(AuthUser user, UUID interviewId) {
        Optional<InterviewEntity> interview = interviewRepository.findById(interviewId);

        if (interview.isEmpty()) {
            throw new NotFoundException("Интервью с id %s не найдено".formatted(interviewId));
        }

        return InterviewUtils.isUserAuthor(user, interview.get());
    }
}
