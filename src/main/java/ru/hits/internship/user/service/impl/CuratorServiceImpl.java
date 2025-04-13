package ru.hits.internship.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import ru.hits.internship.user.mapper.CuratorMapper;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.request.create.CuratorCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.CuratorEditDto;
import ru.hits.internship.user.model.dto.role.response.CuratorDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.CuratorEntity;
import ru.hits.internship.user.repository.CuratorRepository;
import ru.hits.internship.user.repository.UserRepository;
import ru.hits.internship.user.service.CuratorService;
import ru.hits.internship.user.utils.RoleChecker;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CuratorServiceImpl implements CuratorService {

    private final UserRepository userRepository;
    private final CuratorRepository curatorRepository;
    private final CompanyPartnerRepository companyPartnerRepository;

    @Override
    public PagedListDto<CuratorDto> getAllCurators(UUID userId, Pageable pageable) {
        return curatorRepository.findAll(userId, pageable, CuratorMapper.INSTANCE::toDto);
    }

    @Override
    public CuratorDto createCurator(CuratorCreateDto createDto) {
        CompanyPartnerEntity companyPartner = companyPartnerRepository.findById(createDto.companyId())
                .orElseThrow(() -> new NotFoundException(CompanyPartnerEntity.class, createDto.companyId()));

        UserEntity user = userRepository.findByIdOrThrow(createDto.userId());
        RoleChecker.verifyRoleAvailable(user, UserRole.CURATOR);

        CuratorEntity curator = CuratorMapper.INSTANCE.toEntity(user, companyPartner);
        curatorRepository.save(curator);

        return CuratorMapper.INSTANCE.toDto(curator);
    }

    @Override
    public CuratorDto updateCurator(UUID curatorId, CuratorEditDto editDto) {
        CuratorEntity curator = curatorRepository.findById(curatorId)
                .orElseThrow(() -> new NotFoundException(CuratorEntity.class, curatorId));

        CompanyPartnerEntity companyPartner = companyPartnerRepository.findById(editDto.companyId())
                .orElseThrow(() -> new NotFoundException(CompanyPartnerEntity.class, editDto.companyId()));

        CuratorEntity updatedCurator = CuratorMapper.INSTANCE.updateCurator(curator, companyPartner);
        curatorRepository.save(updatedCurator);

        return CuratorMapper.INSTANCE.toDto(updatedCurator);
    }
}