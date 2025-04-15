package ru.hits.internship.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import ru.hits.internship.practice.entity.PracticeEntity;
import ru.hits.internship.practice.mapper.PracticeMapper;
import ru.hits.internship.practice.models.CreatePracticeDto;
import ru.hits.internship.practice.models.PracticeDto;
import ru.hits.internship.practice.models.UpdatePracticeDto;
import ru.hits.internship.practice.repository.PracticeRepository;
import ru.hits.internship.report.entity.ReportEntity;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.response.RoleDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.repository.StudentRepository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PracticeServiceImpl implements PracticeService {
    private final PracticeRepository repository;
    private final CompanyPartnerRepository companyPartnerRepository;
    private final StudentRepository studentRepository;
    private final PracticeMapper mapper;

    @Override
    public PracticeDto getStudentCurrentPractice(UUID studentId) {
        var practice = repository.findByStudentId(studentId)
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
    public PracticeDto createStudentPractice(AuthUser authUser, CreatePracticeDto createPracticeDto) {
        UUID companyId = createPracticeDto.getCompanyId();
        RoleDto studentDto = Optional.of(authUser.roles().get(UserRole.STUDENT))
                .orElseThrow(() -> new BadRequestException("Пользователь не является студентом"));

        var company = companyPartnerRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException(String.format("Компания с id: %s не найдена", companyId)));
        var student = studentRepository.findById(studentDto.id())
                .orElseThrow(() -> new NotFoundException(String.format("Не найден студент с id: %s ", studentDto.id())));
        var practice = new PracticeEntity(student, company);
        var report = new ReportEntity();
        report.setAuthor(student.getUser());
        report.setPractice(practice);
        practice.setReport(report);

        var savedPractice = repository.save(practice);

        return mapper.toDto(savedPractice);
    }

    @Override
    public PracticeDto approveStudentPractice(UUID practiceId) {
        var practice = repository.findById(practiceId)
                .orElseThrow(() -> new NotFoundException(String.format("Практика с id: %s не найдена", practiceId)));

        practice.setIsApproved(true);
        var savedPractice = repository.save(practice);

        return mapper.toDto(savedPractice);
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
}
