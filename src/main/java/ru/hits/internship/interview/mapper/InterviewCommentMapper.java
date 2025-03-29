package ru.hits.internship.interview.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.interview.entity.InterviewCommentEntity;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.models.CreateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;
import ru.hits.internship.interview.models.UpdateInterviewCommentDto;
import ru.hits.internship.interview.repository.InterviewRepository;
import ru.hits.internship.user.entity.UserEntity;
import ru.hits.internship.user.models.user.UserDto;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class InterviewCommentMapper {

    //TODO Прикрутить репозиторий (feature/#3932?)
//    @Autowired
//    protected UserRepository userRepository;

    @Autowired
    protected InterviewRepository interviewRepository;

    //TODO Прикрутить маппер (feature/#3932?)
//    @Autowired
//    protected UserMapper userMapper;

    @Mapping(target = "interview", source = "interviewId", qualifiedByName = "mapInterviewId")
    @Mapping(target = "author", source = "authorId", qualifiedByName = "mapAuthorId")
    @Mapping(target = "content", source = "dto.content")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    public abstract InterviewCommentEntity map(UUID authorId, UUID interviewId, CreateInterviewCommentDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    @Mapping(target = "content", source = "dto.content")
    public abstract void update(@MappingTarget InterviewCommentEntity entity, UpdateInterviewCommentDto dto);

    @Mapping(target = "author", source = "author", qualifiedByName = "mapAuthor")
    public abstract InterviewCommentDto map(InterviewCommentEntity entity);

    @Named("mapAuthor")
    protected UserDto mapAuthor(UserEntity author) {
        //TODO Прикрутить маппер (feature/#3932?)
//        return userMapper.map(author);
        return null;
    }

    @Named("mapAuthorId")
    protected UserEntity mapAuthorId(UUID userId) {
//        if (!userRepository.existsById(userId)) {
//            throw new NotFoundException("Пользователь с id %s не найден".formatted(userId));
//        }
//
//        return userRepository.getReferenceById(userId);
        //TODO Прикрутить репозиторий (feature/#3932?)
        return null;
    }

    @Named("mapInterviewId")
    protected InterviewEntity mapInterviewId(UUID interviewId) {
        if (!interviewRepository.existsById(interviewId)) {
            throw new NotFoundException("Отбор с id %s не найден".formatted(interviewId));
        }

        return interviewRepository.getReferenceById(interviewId);
    }
}
