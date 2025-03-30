package ru.hits.internship.interview.mapper;

import org.mapstruct.*;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.UpdateInterviewDto;
import ru.hits.internship.partner.mapper.CompanyPartnerMapper;
import ru.hits.internship.stack.mapper.StackMapper;

@Mapper(componentModel = "spring", uses = {StackMapper.class, CompanyPartnerMapper.class})
public interface InterviewMapper {

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    InterviewEntity map(CreateInterviewDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void update(@MappingTarget InterviewEntity entity, UpdateInterviewDto dto);

    @Mapping(target = "companyPartner", source = "company")
    InterviewDto map(InterviewEntity entity);
}
