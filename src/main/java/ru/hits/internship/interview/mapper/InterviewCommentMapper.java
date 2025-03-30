package ru.hits.internship.interview.mapper;

import org.mapstruct.*;
import ru.hits.internship.interview.entity.InterviewCommentEntity;
import ru.hits.internship.interview.models.CreateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;
import ru.hits.internship.interview.models.UpdateInterviewCommentDto;

//TODO Прикрутить маппер (feature/#3932?)
@Mapper(componentModel = "spring"/*, uses = {UserMapper.class}*/)
public interface InterviewCommentMapper {

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    InterviewCommentEntity map(CreateInterviewCommentDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void update(@MappingTarget InterviewCommentEntity entity, UpdateInterviewCommentDto dto);

    InterviewCommentDto map(InterviewCommentEntity entity);
}
