package ru.hits.internship.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import ru.hits.internship.practice.mapper.PracticeMapper;
import ru.hits.internship.practice.models.CreatePracticeDto;
import ru.hits.internship.practice.models.PracticeDto;
import ru.hits.internship.practice.models.UpdatePracticeDto;
import ru.hits.internship.practice.repository.PracticeRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PracticeServiceImpl implements PracticeService {
    private final PracticeRepository repository;
    private final CompanyPartnerRepository companyPartnerRepository;
    private final PracticeMapper mapper;

    @Override
    public PracticeDto getStudentCurrentPractice(UUID studentId) {
        var practice = repository.findById(studentId)
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
    public PracticeDto createStudentPractice(CreatePracticeDto createPracticeDto) {
        //TODO("Нужно получать id текущего пользователя после добавления аутентификации")
        UUID companyId = createPracticeDto.getCompanyId();
        var company = companyPartnerRepository.findById(createPracticeDto.getCompanyId())
                .orElseThrow(() -> new NotFoundException(String.format("Компания с id: %s не найдена", companyId)));


        return null;
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
