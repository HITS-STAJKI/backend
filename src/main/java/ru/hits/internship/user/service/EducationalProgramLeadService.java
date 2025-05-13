package ru.hits.internship.user.service;

import ru.hits.internship.user.model.dto.role.request.create.EducationalProgramLeadCreateDto;
import ru.hits.internship.user.model.dto.role.response.EducationalProgramLeadDto;

public interface EducationalProgramLeadService {
    EducationalProgramLeadDto createProgramLead(EducationalProgramLeadCreateDto createDto);
}
