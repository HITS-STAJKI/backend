package ru.hits.internship.partner.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.partner.models.CompanyPartnerDto;
import ru.hits.internship.partner.models.CreateCompanyPartnerDto;
import ru.hits.internship.partner.models.ShortCompanyPartnerDto;
import ru.hits.internship.partner.models.UpdateCompanyPartnerDto;

@Mapper(componentModel = "spring")
public interface CompanyPartnerMapper {

    @Mapping(target = "logoFilename", constant = "ссылка на файл") // TODO: Откуда брать? Пока так
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curator", ignore = true)
    CompanyPartnerEntity toEntity(CreateCompanyPartnerDto createCompanyPartnerDto);

    // TODO: Согласовать вложенный маппинг для куратора и подцепить сюда маппер пользователей (пока нету)
    @Mapping(target = "curator", ignore = true) // Пока что игнорим для билда
    CompanyPartnerDto toDto(CompanyPartnerEntity companyPartnerEntity);

    ShortCompanyPartnerDto toShortDto(CompanyPartnerEntity companyPartnerEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateCompanyPartnerEntity(@MappingTarget CompanyPartnerEntity companyPartner,
                                    UpdateCompanyPartnerDto updateCompanyPartnerDto);
}
