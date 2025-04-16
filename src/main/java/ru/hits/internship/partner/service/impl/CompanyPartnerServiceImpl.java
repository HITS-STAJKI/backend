package ru.hits.internship.partner.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.partner.mapper.CompanyPartnerMapper;
import ru.hits.internship.partner.models.CompanyPartnerDto;
import ru.hits.internship.partner.models.CreateCompanyPartnerDto;
import ru.hits.internship.partner.models.PartnerFilter;
import ru.hits.internship.partner.models.ShortCompanyPartnerDto;
import ru.hits.internship.partner.models.UpdateCompanyPartnerDto;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import ru.hits.internship.partner.service.CompanyPartnerService;
import ru.hits.internship.partner.validator.CompanyPartnerValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
// TODO: не забыть logo компании
public class CompanyPartnerServiceImpl implements CompanyPartnerService {
    private final CompanyPartnerRepository companyPartnerRepository;
    private final CompanyPartnerValidator companyPartnerValidator;
    private final CompanyPartnerMapper companyPartnerMapper;
    private final List<Filter<CompanyPartnerEntity, PartnerFilter>> filters;

    @Override
    @Transactional
    public CompanyPartnerDto createCompanyPartner(CreateCompanyPartnerDto createCompanyPartnerDto) {
        companyPartnerValidator.checkCompanyPartnerAlreadyExists(createCompanyPartnerDto.getName());

        CompanyPartnerEntity companyPartner = companyPartnerMapper.toEntity(createCompanyPartnerDto);
        CompanyPartnerEntity savedCompanyPartner = companyPartnerRepository.save(companyPartner);

        return companyPartnerMapper.toDto(savedCompanyPartner);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedListDto<ShortCompanyPartnerDto> getCompanyPartners(PartnerFilter partnerFilter, Pageable pageable) {
        Specification<CompanyPartnerEntity> specification = Optional.ofNullable(partnerFilter)
                .map(filter -> filters.stream()
                        .map(f -> f.build(filter))
                        .filter(Objects::nonNull)
                        .reduce(Specification.where(null), Specification::and))
                .orElse(Specification.where(null));

        Page<CompanyPartnerEntity> companyPartnersPage = companyPartnerRepository.findAll(specification, pageable);

        return new PagedListDto<>(companyPartnersPage.map(companyPartnerMapper::toShortDto));
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyPartnerDto getCompanyPartner(UUID partnerId) {
        CompanyPartnerEntity companyPartner = companyPartnerRepository.findById(partnerId)
                .orElseThrow(() -> new NotFoundException("Компания-партнер с id %s не найдена".formatted(partnerId)));

        return companyPartnerMapper.toDto(companyPartner);
    }

    @Override
    @Transactional
    public CompanyPartnerDto updateCompanyPartner(UUID partnerId, UpdateCompanyPartnerDto updateCompanyPartnerDto) {
        CompanyPartnerEntity companyPartner = companyPartnerRepository.findById(partnerId)
                .orElseThrow(() -> new NotFoundException("Компания-партнер с id %s не найдена".formatted(partnerId)));

        companyPartnerValidator.checkCompanyPartnerAlreadyExistsNotSame(updateCompanyPartnerDto.getName(), partnerId);

        companyPartnerMapper.updateCompanyPartnerEntity(companyPartner, updateCompanyPartnerDto);
        CompanyPartnerEntity savedCompanyPartner = companyPartnerRepository.save(companyPartner);

        return companyPartnerMapper.toDto(savedCompanyPartner);
    }

    @Override
    @Transactional
    public void deleteCompanyPartner(UUID partnerId) {
        CompanyPartnerEntity companyPartner = companyPartnerRepository.findById(partnerId)
                .orElseThrow(() -> new NotFoundException("Компания-партнер с id %s не найдена".formatted(partnerId)));

        companyPartnerRepository.delete(companyPartner);
    }


}
