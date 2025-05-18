package ru.hits.internship.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.hits.internship.user.model.dto.role.response.EducationalProgramLeadDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.EducationalProgramLeadEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = UserMapper.class)
public interface EducationalProgramLeadMapper {
    EducationalProgramLeadMapper INSTANCE = Mappers.getMapper(EducationalProgramLeadMapper.class);

    EducationalProgramLeadDto toDto(EducationalProgramLeadEntity programLead);

    @Mapping(target = "id", ignore = true)
    EducationalProgramLeadEntity toEntity(UserEntity user);
}