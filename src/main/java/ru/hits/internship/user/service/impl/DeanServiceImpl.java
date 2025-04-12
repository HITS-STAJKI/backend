package ru.hits.internship.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.NotFoundException;
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

@Service
@RequiredArgsConstructor
public class DeanServiceImpl implements DeanService {

    private final UserRepository userRepository;
    private final DeanRepository deanRepository;

    @Override
    public DeanDto createDean(DeanCreateDto createDto) {
        UserEntity user = userRepository.findById(createDto.userId())
                .orElseThrow(() -> new NotFoundException(UserEntity.class, createDto.userId()));

        if (user.getRoles().stream().anyMatch(roleEntity -> roleEntity.getUserRole().equals(UserRole.DEAN))) {
            throw new BadRequestException("User is already has dean role");
        }

        DeanEntity dean = DeanMapper.INSTANCE.toEntity(user);
        deanRepository.save(dean);

        return DeanMapper.INSTANCE.toDto(dean);
    }
}
