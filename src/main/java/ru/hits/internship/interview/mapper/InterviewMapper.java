package ru.hits.internship.interview.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.UpdateInterviewDto;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.partner.mapper.CompanyPartnerMapper;
import ru.hits.internship.partner.models.ShortCompanyPartnerDto;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import ru.hits.internship.stack.entity.StackEntity;
import ru.hits.internship.stack.mapper.StackMapper;
import ru.hits.internship.stack.models.StackDto;
import ru.hits.internship.stack.repository.StackRepository;
import ru.hits.internship.user.entity.role.StudentEntity;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class InterviewMapper {

    //TODO Прикрутить репозиторий (feature/#3932?)
//    @Autowired
//    protected StudentRepository studentRepository;

    @Autowired
    protected StackRepository stackRepository;

    @Autowired
    protected CompanyPartnerRepository companyPartnerRepository;

    @Autowired
    protected StackMapper stackMapper;

    @Autowired
    protected CompanyPartnerMapper companyPartnerMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stack", source = "dto.stackId", qualifiedByName = "mapStackId")
    @Mapping(target = "student", source = "studentId", qualifiedByName = "mapStudentId")
    @Mapping(target = "company", source = "dto.companyPartnerId", qualifiedByName = "mapCompanyPartnerId")
    @Mapping(target = "comments", ignore = true)
    public abstract InterviewEntity map(UUID studentId, CreateInterviewDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    @Mapping(target = "stack", source = "stackId", qualifiedByName = "mapStackId")
    public abstract void update(@MappingTarget InterviewEntity entity, UpdateInterviewDto dto);

    @Mapping(target = "stack", source = "stack", qualifiedByName = "mapStack")
    @Mapping(target = "companyPartner", source = "company", qualifiedByName = "mapCompany")
    public abstract InterviewDto map(InterviewEntity entity);

    @Named("mapStack")
    protected StackDto mapStack(StackEntity stack) {
        return stackMapper.map(stack);
    }

    @Named("mapCompany")
    protected ShortCompanyPartnerDto mapCompany(CompanyPartnerEntity company) {
        return companyPartnerMapper.toShortDto(company);
    }

    @Named("mapStudentId")
    protected StudentEntity mapStudentId(UUID studentId) {
//        if (!studentRepository.existsById(studentId)) {
//            throw new NotFoundException("Студент с id %s не найден".formatted(studentId));
//        }
//
//        return studentRepository.getReferenceById(studentId);
        //TODO Прикрутить репозиторий (feature/#3932?)
        return null;
    }

    @Named("mapStackId")
    protected StackEntity mapStackId(UUID stackId) {
        if (!stackRepository.existsById(stackId)) {
            throw new NotFoundException("Стек с id %s не найден".formatted(stackId));
        }

        return stackRepository.getReferenceById(stackId);
    }

    @Named("mapCompanyPartnerId")
    protected CompanyPartnerEntity mapCompanyPartnerId(UUID companyPartnerId) {
        if (!companyPartnerRepository.existsById(companyPartnerId)) {
            throw new NotFoundException("Партнер с id %s не найден".formatted(companyPartnerId));
        }

        return companyPartnerRepository.getReferenceById(companyPartnerId);
    }
}
