package ru.hits.internship.interview.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.hits.internship.interview.entity.InterviewCommentEntity;
import ru.hits.internship.interview.models.CreateInterviewCommentDto;
import ru.hits.internship.interview.models.InterviewCommentDto;
import ru.hits.internship.interview.models.UpdateInterviewCommentDto;
import ru.hits.internship.user.mapper.UserMapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface InterviewCommentMapper {

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    InterviewCommentEntity map(CreateInterviewCommentDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void update(@MappingTarget InterviewCommentEntity entity, UpdateInterviewCommentDto dto);

    InterviewCommentDto map(InterviewCommentEntity entity);
}
