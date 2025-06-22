package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.user.model.dto.role.request.create.EducationalProgramLeadCreateDto;
import ru.hits.internship.user.model.dto.role.response.EducationalProgramLeadDto;
import ru.hits.internship.user.service.EducationalProgramLeadService;

@RestController
@Tag(name = "Educational program lead")
@RequestMapping(value = "/api/v1/educational-program-lead")
@RequiredArgsConstructor
public class EducationalProgramLeadController {

    private final EducationalProgramLeadService programLeadService;

    @Operation(summary = "Создание руководителя")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('DEAN', 'EDUCATIONAL_PROGRAM_LEAD')")
    @PostMapping
    public EducationalProgramLeadDto createProgramLead(@RequestBody @Valid EducationalProgramLeadCreateDto createDto) {
        return programLeadService.createProgramLead(createDto);
    }
}
