package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.user.model.dto.role.request.create.CuratorCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.CuratorEditDto;
import ru.hits.internship.user.model.dto.role.response.CuratorDto;

import java.util.UUID;

@RestController
@Tag(name = "Куратор", description = "Отвечает за работу с кураторами")
@RequestMapping(value = "/api/v1/curator")
public class CuratorController {

    @Operation(summary = "Получение информации о кураторе текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public CuratorDto getCurrentCurator() {
        return null;
    }

    @Operation(summary = "Получение информации о кураторе")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public CuratorDto getCuratorById(@PathVariable UUID id) {
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
