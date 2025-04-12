package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.DeanCreateDto;
import ru.hits.internship.user.model.dto.role.response.DeanDto;

@RestController
@Tag(name = "Деканат", description = "Отвечает за работу с представителями деканата")
@RequestMapping(value = "/api/v1/dean")
public class DeanController {

    @Operation(summary = "Получение всех представителей деканата")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<DeanDto> getAllDeans(@ParameterObject Pageable pageable) {
        return null;
    }

    @Operation(summary = "Создание представителя деканата")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public DeanDto createDean(@RequestBody @Valid DeanCreateDto createDto) {
        return null;
    }
}
