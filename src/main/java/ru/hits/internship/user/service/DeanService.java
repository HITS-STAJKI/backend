package ru.hits.internship.user.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.DeanCreateDto;
import ru.hits.internship.user.model.dto.role.response.DeanDto;

import java.util.UUID;

public interface DeanService {
    DeanDto createDean(DeanCreateDto createDto);
    PagedListDto<DeanDto> getAllDeans(UUID userId, String fullName, Pageable pageable);
}
