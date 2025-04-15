package ru.hits.internship.interview.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.UpdateInterviewDto;
import ru.hits.internship.language.mapper.LanguageMapper;
import ru.hits.internship.partner.mapper.CompanyPartnerMapper;
import ru.hits.internship.stack.mapper.StackMapper;
import ru.hits.internship.user.mapper.UserMapper;

@Mapper(componentModel = "spring", uses = {StackMapper.class, CompanyPartnerMapper.class, LanguageMapper.class, UserMapper.class})
public interface InterviewMapper {

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    InterviewEntity map(CreateInterviewDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void update(@MappingTarget InterviewEntity entity, UpdateInterviewDto dto);

    @Mapping(target = "companyPartner", source = "company")
    @Mapping(target = "student", source = "student.user")
    InterviewDto map(InterviewEntity entity);
}
