package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.CuratorCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.CuratorEditDto;
import ru.hits.internship.user.model.dto.role.response.CuratorDto;

@RestController
@Tag(name = "Куратор", description = "Отвечает за работу с кураторами")
@RequestMapping(value = "/api/v1/curator")
public class CuratorController {

    @Operation(summary = "Получение всех кураторов")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<CuratorDto> getAllCurators(@ParameterObject Pageable pageable) {
        return null;
    }

    @Operation(summary = "Создание куратора")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public CuratorDto createCurator(@RequestBody @Valid CuratorCreateDto createDto) {
        return null;
    }

    @Operation(summary = "Обновление информации о кураторе")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public CuratorDto updateCuratorRole(
            @PathVariable String id,
            @RequestBody @Valid CuratorEditDto editDto
    ) {
        return null;
    }
}
