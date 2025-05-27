package ru.hits.internship.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.group.repository.GroupRepository;
import ru.hits.internship.interview.models.StatusEnum;
import ru.hits.internship.interview.repository.InterviewRepository;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import ru.hits.internship.practice.entity.PracticeEntity;
import ru.hits.internship.practice.mapper.PracticeMapper;
import ru.hits.internship.practice.models.CreatePracticeDto;
import ru.hits.internship.practice.models.PracticeDto;
import ru.hits.internship.practice.models.UpdatePracticeDto;
import ru.hits.internship.practice.models.filter.GetAllPracticeFilter;
import ru.hits.internship.practice.repository.PracticeRepository;
import ru.hits.internship.practice.specification.PracticeSpecification;
import ru.hits.internship.report.entity.ReportEntity;
import ru.hits.internship.stack.repository.StackRepository;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.response.RoleDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PracticeServiceImpl implements PracticeService {
    private final PracticeRepository repository;
    private final CompanyPartnerRepository companyPartnerRepository;
    private final InterviewRepository interviewRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final StackRepository stackRepository;
    private final PracticeMapper mapper;

    @Override
    public PracticeDto getStudentCurrentPractice(UUID studentId) {
        var practice = repository.findByStudentIdAndIsArchivedFalse(studentId)
                .orElseThrow(() -> new NotFoundException(String.format("У студента с id: %s отсутствует практика", studentId)));

        return mapper.toDto(practice);
    }

    @Override
    public PagedListDto<PracticeDto> getStudentPractices(UUID studentId, Pageable pageable) {
        Page<PracticeDto> practices = repository.findAllByStudentId(studentId, pageable)
                .map(mapper::toDto);

        return new PagedListDto<>(practices);
    }

    @Override
    public PagedListDto<PracticeDto> getAllPractices(GetAllPracticeFilter filter, Pageable pageable) {
        Specification<PracticeEntity> specification = Specification
                .where(PracticeSpecification.hasReport(filter.getHasReport()))
                .and(PracticeSpecification.isReportApproved(filter.getIsReportApproved()))
                .and(PracticeSpecification.hasCompanyId(filter.getCompanyId()))
                .and(PracticeSpecification.hasGroupInGroupIds(filter.getGroupIds()))
                .and(PracticeSpecification.isArchived(filter.getIsArchived()))
                .and(PracticeSpecification.hasStudentName(filter.getStudentName()))
                .and(PracticeSpecification.isPracticeApproved(filter.getIsPracticeApproved()));

        Page<PracticeEntity> practicesPage = repository.findAll(specification, pageable);

        return new PagedListDto<>(practicesPage.map(mapper::toDto));
    }

    @Override
    public PracticeDto createStudentPractice(AuthUser authUser, CreatePracticeDto createPracticeDto) {
        UUID interviewId = createPracticeDto.getInterviewId();

        RoleDto studentDto = Optional.of(authUser.roles().get(UserRole.STUDENT))
                .orElseThrow(() -> new BadRequestException("Пользователь не является студентом"));

        repository.findByStudentIdAndIsArchivedFalse(studentDto.id())
                .ifPresent(p -> {
                    throw new BadRequestException("Студент уже находится на практике");
                });

        var interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Собеседование не найдено"));

        if (!interview.getStudent().getId().equals(studentDto.id())) {
            throw new BadRequestException("Студент не связан с этим собеседованием");
        }

        if (!interview.getStatus().equals(StatusEnum.SUCCEED)) {
            throw new BadRequestException("Собеседование не было успешно пройдено");
        }

        var practice = new PracticeEntity(
                interview.getStudent(),
                interview.getCompany(),
                interview.getStack()
        );

        practice.setIsPaid(createPracticeDto.getIsPaid());

        var savedPractice = repository.save(practice);

        return mapper.toDto(savedPractice);
    }


    @Override
    public PracticeDto approveStudentPractice(UUID studentId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(String.format("Не найден студент с id: %s ", studentId)));
        var practice = repository.findByStudentIdAndIsArchivedFalse(studentId)
                .orElseThrow(() -> new NotFoundException(String.format("У студента с id: %s не найдена практика", studentId)));

        practice.setIsApproved(true);

        var report = new ReportEntity();
        report.setAuthor(student.getUser());
        report.setPractice(practice);
        practice.setReport(report);

        var savedPractice = repository.save(practice);

        return mapper.toDto(savedPractice);
    }

    @Override
    @Transactional
    public Response approveAllStudentPracticesForCompany(UUID companyId) {
        var practices = repository.findAllByCompanyIdAndIsApprovedFalse(companyId);
        practices.forEach(practice -> {
            practice.setIsApproved(true);

            var report = new ReportEntity();
            report.setAuthor(practice.getStudent().getUser());
            report.setPractice(practice);
            practice.setReport(report);
        });
        repository.saveAll(practices);

        return new Response(
                String.format("Все неподтвержденные практики студентов компании c id %s были подтверждены", companyId.toString()),
                HttpStatus.OK.value()
        );
    }

    @Override
    @Transactional
    public Response approveStudentPractices(List<UUID> practiceIds) {
        var practices = repository.findAllByIdInAndIsApprovedFalse(practiceIds);
        practices.forEach(practice -> {
            practice.setIsApproved(true);

            var report = new ReportEntity();
            report.setAuthor(practice.getStudent().getUser());
            report.setPractice(practice);
            practice.setReport(report);
        });
        repository.saveAll(practices);

        return new Response(
                "Практики были подтверждены",
                HttpStatus.OK.value()
        );
    }

    @Override
    public PagedListDto<PracticeDto> getPracticeRequests(Pageable pageable) {
        var unapprovedPracticeDtos = repository.findAllByIsApprovedFalse(pageable)
                .map(mapper::toDto);

        return new PagedListDto<>(unapprovedPracticeDtos);
    }

    @Override
    public PracticeDto updatePractice(UUID practiceId, UpdatePracticeDto updatePracticeDto) {
        var practice = repository.findById(practiceId)
                .orElseThrow(() -> new NotFoundException(String.format("Практика с id: %s не найдена", practiceId)));

        practice.setIsPaid(updatePracticeDto.getIsPaid());
        var updatedPractice = repository.save(practice);

        return mapper.toDto(updatedPractice);
    }

    @Override
    public PracticeDto archiveStudentPractice(UUID practiceId) {
        var practice = repository.findById(practiceId)
                .orElseThrow(() -> new NotFoundException(String.format("Практика с id: %s не найдена", practiceId)));

        practice.setIsArchived(true);
        var archivedPractice = repository.save(practice);

        return mapper.toDto(archivedPractice);
    }

    @Override
    @Transactional
    public Response archiveAllStudentPracticesForGroup(UUID groupId) {
        groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(GroupEntity.class, groupId));

        int archivedPracticeCount = repository.archivePracticesByGroup(groupId);

        return new Response(String.format("Успешно архивировано %d практик", archivedPracticeCount), HttpStatus.OK.value());
    }
}
