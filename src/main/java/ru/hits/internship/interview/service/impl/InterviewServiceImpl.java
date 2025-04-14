package ru.hits.internship.interview.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.ForbiddenException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.mapper.InterviewMapper;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.InterviewFilter;
import ru.hits.internship.interview.models.UpdateInterviewDto;
import ru.hits.internship.interview.repository.InterviewRepository;
import ru.hits.internship.interview.service.InterviewService;
import ru.hits.internship.language.entity.LanguageEntity;
import ru.hits.internship.language.repository.LanguageRepository;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import ru.hits.internship.stack.entity.StackEntity;
import ru.hits.internship.stack.repository.StackRepository;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.repository.UserRepository;

import java.util.*;

import static ru.hits.internship.interview.service.common.InterviewUtils.getStudentIdIfExists;
import static ru.hits.internship.interview.service.common.InterviewUtils.isUserAuthor;
import static ru.hits.internship.user.utils.RoleChecker.isUserHasRole;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewMapper interviewMapper;
    private final StackRepository stackRepository;
    private final LanguageRepository languageRepository;
    private final CompanyPartnerRepository companyPartnerRepository;
    private final UserRepository userRepository;
    private final List<Filter<InterviewEntity, InterviewFilter>> filters;

    @Override
    @Transactional
    public InterviewDto createInterview(AuthUser user, CreateInterviewDto createInterviewDto) {
        InterviewEntity entity = interviewMapper.map(createInterviewDto);

        StackEntity stack = getStackReference(createInterviewDto.getStackId());
        entity.setStack(stack);

        List<LanguageEntity> languages = getLanguageReferences(createInterviewDto.getLanguageIds());
        entity.setLanguages(languages);

        CompanyPartnerEntity companyPartner = getCompanyPartnerReference(createInterviewDto.getCompanyPartnerId());
        entity.setCompany(companyPartner);

        UserEntity userEntity = userRepository.findById(user.id())
                .orElseThrow(() -> new NotFoundException("Пользователь с id %s не найден".formatted(user.id())));
        StudentEntity student = userEntity.getRoles()
                .stream()
                .filter(role -> role.getUserRole() == UserRole.STUDENT)
                .map(role -> (StudentEntity) role)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Пользователь не имеет роли студента"));
        entity.setStudent(student);

        InterviewEntity savedEntity = interviewRepository.saveAndFlush(entity);
        return interviewMapper.map(savedEntity);
    }

    @Override
    @Transactional
    public InterviewDto updateInterview(AuthUser user, UUID interviewId, UpdateInterviewDto updateInterviewDto) {
        InterviewEntity interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Интервью с id %s не найдено".formatted(interviewId)));

        if (!isUserAuthor(user, interview) && !isUserHasRole(user, UserRole.DEAN)) {
            throw new ForbiddenException();
        }

        interviewMapper.update(interview, updateInterviewDto);

        StackEntity stack = getStackReference(updateInterviewDto.getStackId());
        interview.setStack(stack);

        List<LanguageEntity> languages = getLanguageReferences(updateInterviewDto.getLanguageIds());
        interview.setLanguages(languages);

        InterviewEntity savedInterview = interviewRepository.saveAndFlush(interview);

        return interviewMapper.map(savedInterview);
    }

    @Override
    @Transactional
    public void deleteInterview(AuthUser user, UUID interviewId) {
        InterviewEntity interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Интервью с id %s не найдено".formatted(interviewId)));

        if (!isUserAuthor(user, interview) && !isUserHasRole(user, UserRole.DEAN)) {
            throw new ForbiddenException();
        }

        interviewRepository.delete(interview);
    }

    @Override
    @Transactional(readOnly = true)
    public InterviewDto getInterview(AuthUser user, UUID interviewId) {
        InterviewEntity interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Интервью с id %s не найдено".formatted(interviewId)));

        if (!isUserAuthor(user, interview) && !isUserHasRole(user, UserRole.DEAN) && !isUserHasRole(user, UserRole.CURATOR)) {
            throw new ForbiddenException();
        }

        return interviewMapper.map(interview);
    }

    @Override
    public PagedListDto<InterviewDto> getInterviewList(AuthUser user, InterviewFilter interviewFilter, Pageable pageable) {
        if (!isUserHasRole(user, UserRole.DEAN) && !isUserHasRole(user, UserRole.CURATOR)) {
            throw new ForbiddenException();
        }

        Specification<InterviewEntity> specification = Optional.ofNullable(interviewFilter)
                .map(filter -> filters.stream()
                        .map(f -> f.build(filter))
                        .filter(Objects::nonNull)
                        .reduce(Specification.where(null), Specification::and))
                .orElse(Specification.where(null));

        Page<InterviewEntity> page = interviewRepository.findAll(specification, pageable);

        return new PagedListDto<>(page.map(interviewMapper::map));
    }

    @Override
    public PagedListDto<InterviewDto> getInterviewList(AuthUser user, Pageable pageable) {
        Optional<UUID> studentId = getStudentIdIfExists(user);

        if (studentId.isEmpty()) {
            throw new NotFoundException("Пользователь не имеет роли студента");
        }

        Page<InterviewEntity> page = interviewRepository.findAllByStudentId(studentId.get(), pageable);

        return new PagedListDto<>(page.map(interviewMapper::map));
    }

    private CompanyPartnerEntity getCompanyPartnerReference(UUID companyPartnerId) {
        if (!companyPartnerRepository.existsById(companyPartnerId)) {
            throw new NotFoundException("Партнер с id %s не найден".formatted(companyPartnerId));
        }
        return companyPartnerRepository.getReferenceById(companyPartnerId);
    }

    private StackEntity getStackReference(UUID stackId) {
        if (!stackRepository.existsById(stackId)) {
            throw new NotFoundException("Стек с id %s не найден".formatted(stackId));
        }
        return stackRepository.getReferenceById(stackId);
    }

    private List<LanguageEntity> getLanguageReferences(List<UUID> languageIds) {
        List<LanguageEntity> languages = new ArrayList<>();
        for (UUID languageId : languageIds) {
            if (!languageRepository.existsById(languageId)) {
                throw new NotFoundException("Язык с id %s не найден".formatted(languageId));
            }
            LanguageEntity language = languageRepository.getReferenceById(languageId);
            languages.add(language);
        }
        return languages;
    }
}
