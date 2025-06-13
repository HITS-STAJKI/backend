package ru.hits.internship.partner.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.partner.models.CompanyPartnerDto;
import ru.hits.internship.partner.models.CreateCompanyPartnerDto;
import ru.hits.internship.partner.models.ShortCompanyPartnerDto;
import ru.hits.internship.partner.models.UpdateCompanyPartnerDto;
import ru.hits.internship.user.mapper.UserMapper;
import ru.hits.internship.user.model.dto.user.UserShortDto;
import ru.hits.internship.user.model.entity.role.CuratorEntity;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface CompanyPartnerMapper {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curators", ignore = true)
    @Mapping(target = "practices", ignore = true)
    CompanyPartnerEntity toEntity(CreateCompanyPartnerDto createCompanyPartnerDto);

    @Mapping(target = "curators", source = "companyPartnerEntity.curators", qualifiedByName = "mapCurators")
    CompanyPartnerDto toDto(CompanyPartnerEntity companyPartnerEntity);

    @Named("mapCurators")
    default List<UserShortDto> mapCurators(List<CuratorEntity> curators) {
        if (curators == null) {
            return Collections.emptyList();
        }

        return curators.stream()
                .map(CuratorEntity::getUser)
                .map(userMapper::toShortDto)
                .toList();}

    ShortCompanyPartnerDto toShortDto(CompanyPartnerEntity companyPartnerEntity);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateCompanyPartnerEntity(@MappingTarget CompanyPartnerEntity companyPartner,
                                    UpdateCompanyPartnerDto updateCompanyPartnerDto);
}
