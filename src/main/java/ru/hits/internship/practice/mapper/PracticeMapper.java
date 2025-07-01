package ru.hits.internship.practice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.internship.group.mapper.GroupMapper;
import ru.hits.internship.partner.mapper.CompanyPartnerMapper;
import ru.hits.internship.practice.entity.PracticeEntity;
import ru.hits.internship.practice.models.PracticeDto;
import ru.hits.internship.user.mapper.UserMapper;

@Mapper(componentModel = "spring", uses = {CompanyPartnerMapper.class, UserMapper.class, GroupMapper.class})
public interface PracticeMapper {
    @Mapping(target = "user", source = "student.user")
    @Mapping(target = "group", source = "student.group")
    @Mapping(target = "isReportAttached", expression = "java(entity.getReport() != null && entity.getReport().getFileId() != null)")
    @Mapping(target = "reportGrade", expression = "java(entity.getReport() != null ? entity.getReport().getGrade() : null)")
    PracticeDto toDto(PracticeEntity entity);
}
