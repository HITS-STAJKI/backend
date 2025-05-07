package ru.hits.internship.practice.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.practice.models.CreatePracticeDto;
import ru.hits.internship.practice.models.PracticeDto;
import ru.hits.internship.practice.models.UpdatePracticeDto;
import ru.hits.internship.practice.models.filter.GetAllPracticeFilter;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.List;
import java.util.UUID;

public interface PracticeService {
    PracticeDto getStudentCurrentPractice(UUID studentId);

    PagedListDto<PracticeDto> getStudentPractices(UUID studentId, Pageable pageable);

    PagedListDto<PracticeDto> getAllPractices(GetAllPracticeFilter filter, Pageable pageable);

    PracticeDto createStudentPractice(AuthUser user, CreatePracticeDto createPracticeDto);

    PracticeDto approveStudentPractice(UUID studentId);

    Response approveAllStudentPracticesForCompany(UUID companyId);

    Response approveStudentPractices(List<UUID> practiceIds);

    PagedListDto<PracticeDto> getPracticeRequests(Pageable pageable);

    PracticeDto updatePractice(UUID practiceId, UpdatePracticeDto updatePracticeDto);

    PracticeDto archiveStudentPractice(UUID practiceId);
}
