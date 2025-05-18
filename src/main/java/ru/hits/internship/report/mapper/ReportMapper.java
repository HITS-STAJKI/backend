package ru.hits.internship.report.mapper;

import org.mapstruct.Mapper;
import ru.hits.internship.report.entity.ReportEntity;
import ru.hits.internship.report.models.ReportDto;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportDto toDto(ReportEntity entity);
}
