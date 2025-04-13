package ru.hits.internship.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.mapper.DeanMapper;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.request.create.DeanCreateDto;
import ru.hits.internship.user.model.dto.role.response.DeanDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.DeanEntity;
import ru.hits.internship.user.repository.DeanRepository;
import ru.hits.internship.user.repository.UserRepository;
import ru.hits.internship.user.service.DeanService;
import ru.hits.internship.user.utils.RoleChecker;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeanServiceImpl implements DeanService {

    private final UserRepository userRepository;
    private final DeanRepository deanRepository;

    @Override
    public PagedListDto<DeanDto> getAllDeans(UUID userId, Pageable pageable) {
        return deanRepository.findAll(userId, pageable, DeanMapper.INSTANCE::toDto);
    }

    @Override
    public DeanDto createDean(DeanCreateDto createDto) {
        UserEntity user = userRepository.findByIdOrThrow(createDto.userId());
        RoleChecker.verifyRoleAvailable(user, UserRole.DEAN);

        DeanEntity dean = DeanMapper.INSTANCE.toEntity(user);
        deanRepository.save(dean);

        return DeanMapper.INSTANCE.toDto(dean);
    }
}
