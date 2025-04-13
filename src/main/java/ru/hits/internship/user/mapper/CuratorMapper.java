package ru.hits.internship.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.partner.entity.CompanyPartnerEntity;
import ru.hits.internship.partner.mapper.CompanyPartnerMapper;
import ru.hits.internship.user.model.dto.role.response.CuratorDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.CuratorEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { UserMapper.class, CompanyPartnerMapper.class })
public interface CuratorMapper {
    CuratorMapper INSTANCE = Mappers.getMapper(CuratorMapper.class);

    CuratorDto toDto(CuratorEntity dean);

    @Mapping(target = "id", ignore = true)
    CuratorEntity toEntity(UserEntity user, CompanyPartnerEntity companyPartner);

    @Mapping(target = "id", ignore = true)
    CuratorEntity updateCurator(@MappingTarget CuratorEntity curator, CompanyPartnerEntity companyPartner);
}