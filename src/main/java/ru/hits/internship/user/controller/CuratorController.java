package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.CuratorCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.CuratorEditDto;
import ru.hits.internship.user.model.dto.role.response.CuratorDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.service.CuratorService;

import java.util.UUID;

@RestController
@Tag(name = "Curator", description = "Отвечает за работу с кураторами")
@RequestMapping(value = "/api/v1/curator")
@RequiredArgsConstructor
public class CuratorController {

    private final CuratorService curatorService;

    @Operation(summary = "Создание куратора")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PostMapping
    public CuratorDto createCurator(@RequestBody @Valid CuratorCreateDto createDto) {
        return curatorService.createCurator(createDto);
    }

    @Operation(summary = "Обновление информации о кураторе")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PutMapping("/{id}")
    public CuratorDto updateCuratorRole(
            @PathVariable UUID id,
            @RequestBody @Valid CuratorEditDto editDto
    ) {
        return curatorService.updateCurator(id, editDto);
    }

    @Operation(summary = "Получение всех кураторов")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('DEAN', 'EDUCATIONAL_PROGRAM_LEAD')")
    @GetMapping("/list")
    public PagedListDto<CuratorDto> getAllCurators(
            @AuthenticationPrincipal AuthUser authUser,
            @ParameterObject Pageable pageable,
            @RequestParam(required = false) String fullName
    ) {
        return curatorService.getAllCurators(authUser.id(), fullName, pageable);
    }
}
