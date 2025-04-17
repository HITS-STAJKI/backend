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
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.DeanCreateDto;
import ru.hits.internship.user.model.dto.role.response.DeanDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.service.DeanService;

@RestController
@Tag(name = "Деканат", description = "Отвечает за работу с представителями деканата")
@RequestMapping(value = "/api/v1/dean")
@RequiredArgsConstructor
public class DeanController {

    private final DeanService deanService;

    @Operation(summary = "Получение всех представителей деканата")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @GetMapping("/list")
    public PagedListDto<DeanDto> getAllDeans(
            @AuthenticationPrincipal AuthUser authUser,
            @ParameterObject Pageable pageable,
            @RequestParam(required = false) String fullName
    ) {
        return deanService.getAllDeans(authUser.id(), fullName, pageable);
    }

    @Operation(summary = "Создание представителя деканата")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEAN')")
    @PostMapping
    public DeanDto createDean(@RequestBody @Valid DeanCreateDto createDto) {
        return deanService.createDean(createDto);
    }
}
