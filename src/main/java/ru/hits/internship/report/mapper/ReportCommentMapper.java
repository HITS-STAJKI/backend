package ru.hits.internship.report.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.internship.report.entity.ReportCommentEntity;
import ru.hits.internship.report.entity.ReportEntity;
import ru.hits.internship.report.models.CreateReportCommentDto;
import ru.hits.internship.report.models.ReportCommentDto;
import ru.hits.internship.user.mapper.UserMapper;
import ru.hits.internship.user.model.entity.UserEntity;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ReportCommentMapper {
    ReportCommentDto toDto(ReportCommentEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "report", source = "report")
    ReportCommentEntity toEntity(CreateReportCommentDto createReportCommentDto, UserEntity author, ReportEntity report);
}
