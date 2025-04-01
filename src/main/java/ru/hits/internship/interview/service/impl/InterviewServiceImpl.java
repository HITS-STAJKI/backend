package ru.hits.internship.interview.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.mapper.InterviewMapper;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.UpdateInterviewDto;
import ru.hits.internship.interview.repository.InterviewRepository;
import ru.hits.internship.interview.service.InterviewService;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import ru.hits.internship.stack.entity.StackEntity;
import ru.hits.internship.stack.repository.StackRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewMapper interviewMapper;
    private final StackRepository stackRepository;
    private final CompanyPartnerRepository companyPartnerRepository;
    //TODO Прикрутить репозиторий (feature/#3932?)
    //private final StudentRepository studentRepository;

    @Override
    @Transactional
    public InterviewDto createInterview(UUID studentId, CreateInterviewDto createInterviewDto) {
        InterviewEntity entity = interviewMapper.map(createInterviewDto);

        UUID stackId = createInterviewDto.getStackId();
        if (!stackRepository.existsById(stackId)) {
            throw new NotFoundException("Стек с id %s не найден".formatted(stackId));
        }
        StackEntity stack = stackRepository.getReferenceById(stackId);
        entity.setStack(stack);

        UUID companyPartnerId = createInterviewDto.getCompanyPartnerId();
        if (!companyPartnerRepository.existsById(companyPartnerId)) {
            throw new NotFoundException("Партнер с id %s не найден".formatted(companyPartnerId));
        }
        CompanyPartnerEntity companyPartner = companyPartnerRepository.getReferenceById(companyPartnerId);
        entity.setCompany(companyPartner);

        //TODO Прикрутить репозиторий (feature/#3932?)
        //if (!studentRepository.existsById(studentId)) {
        //    throw new NotFoundException("Студент с id %s не найден".formatted(studentId));
        //}
        //StudentEntity student = studentRepository.getReferenceById(studentId);
        //entity.setStudent(student);

        InterviewEntity savedEntity = interviewRepository.save(entity);
        return interviewMapper.map(savedEntity);
    }

    @Override
    @Transactional
    public InterviewDto updateInterview(UUID interviewId, UpdateInterviewDto updateInterviewDto) {
        InterviewEntity interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Интервью с id %s не найдено".formatted(interviewId)));

        interviewMapper.update(interview, updateInterviewDto);

        UUID stackId = updateInterviewDto.getStackId();
        if (!stackRepository.existsById(stackId)) {
            throw new NotFoundException("Стек с id %s не найден".formatted(stackId));
        }
        StackEntity stack = stackRepository.getReferenceById(stackId);
        interview.setStack(stack);

        InterviewEntity savedInterview = interviewRepository.save(interview);

        return interviewMapper.map(savedInterview);
    }

    @Override
    @Transactional
    public void deleteInterview(UUID interviewId) {
        InterviewEntity interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Интервью с id %s не найдено".formatted(interviewId)));

        interviewRepository.delete(interview);
    }

    @Override
    @Transactional(readOnly = true)
    public InterviewDto getInterview(UUID interviewId) {
        return interviewRepository.findById(interviewId)
                .map(interviewMapper::map)
                .orElseThrow(() -> new NotFoundException("Интервью с id %s не найдено".formatted(interviewId)));
    }

    @Override
    @Transactional(readOnly = true)
    public PagedListDto<InterviewDto> getInterviewList(UUID studentId, Pageable pageable) {
        Page<InterviewEntity> page = interviewRepository.findAllByStudentId(studentId, pageable);

        return new PagedListDto<>(page.map(interviewMapper::map));
    }
}
