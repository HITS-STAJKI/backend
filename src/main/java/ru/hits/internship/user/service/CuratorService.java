package ru.hits.internship.user.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.CuratorCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.CuratorEditDto;
import ru.hits.internship.user.model.dto.role.response.CuratorDto;

import java.util.UUID;

public interface CuratorService {
    CuratorDto updateCurator(UUID curatorId, CuratorEditDto editDto);
    CuratorDto createCurator(CuratorCreateDto createDto);
    PagedListDto<CuratorDto> getAllCurators(UUID userId, String fullName, Pageable pageable);
}
