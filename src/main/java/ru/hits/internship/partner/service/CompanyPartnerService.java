package ru.hits.internship.partner.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.partner.models.CompanyPartnerDto;
import ru.hits.internship.partner.models.CreateCompanyPartnerDto;
import ru.hits.internship.partner.models.ShortCompanyPartnerDto;
import ru.hits.internship.partner.models.UpdateCompanyPartnerDto;
import java.util.UUID;

public interface CompanyPartnerService {
    CompanyPartnerDto createCompanyPartner(CreateCompanyPartnerDto createCompanyPartnerDto);

    PagedListDto<ShortCompanyPartnerDto> getCompanyPartners(Pageable pageable);

    CompanyPartnerDto getCompanyPartner(UUID partnerId);

    CompanyPartnerDto updateCompanyPartner(UUID partnerId, UpdateCompanyPartnerDto updateCompanyPartnerDto);

    void deleteCompanyPartner(UUID partnerId);
}
