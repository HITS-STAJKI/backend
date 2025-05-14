package ru.hits.internship.user.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.internship.user.mapper.EducationalProgramLeadMapper;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.request.create.EducationalProgramLeadCreateDto;
import ru.hits.internship.user.model.dto.role.response.EducationalProgramLeadDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.EducationalProgramLeadEntity;
import ru.hits.internship.user.repository.EducationalProgramLeadRepository;
import ru.hits.internship.user.repository.UserRepository;
import ru.hits.internship.user.service.EducationalProgramLeadService;
import ru.hits.internship.user.utils.RoleChecker;

@Service
@AllArgsConstructor
public class EducationalProgramLeadServiceImpl implements EducationalProgramLeadService {

    private final UserRepository userRepository;
    private final EducationalProgramLeadRepository programLeadRepository;

    @Override
    public EducationalProgramLeadDto createProgramLead(EducationalProgramLeadCreateDto createDto) {
        UserEntity user = userRepository.findByIdOrThrow(createDto.userId());
        RoleChecker.verifyRoleAvailable(user, UserRole.EDUCATIONAL_PROGRAM_LEAD);

        EducationalProgramLeadEntity programLead = EducationalProgramLeadMapper.INSTANCE.toEntity(user);
        programLeadRepository.save(programLead);

        return EducationalProgramLeadMapper.INSTANCE.toDto(programLead);
    }
}
