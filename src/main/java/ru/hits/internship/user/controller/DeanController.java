package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.user.model.dto.role.request.create.DeanCreateDto;
import ru.hits.internship.user.model.dto.role.response.DeanDto;

import java.util.List;

@RestController
@Tag(name = "Деканат", description = "Отвечает за работу с представителями деканата")
@RequestMapping(value = "/api/v1/dean")
public class DeanController {

    @Operation(summary = "Получение всех представителей деканата")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<DeanDto> getAllDeans() {
        return null;
    }

    @Operation(summary = "Создание представителя деканата")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public DeanDto createDean(@RequestBody @Valid DeanCreateDto createDto) {
        return null;
    }
}
