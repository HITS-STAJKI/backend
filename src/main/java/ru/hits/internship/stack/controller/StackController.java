package ru.hits.internship.stack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.stack.models.CreateStackDto;
import ru.hits.internship.stack.models.StackDto;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Стек", description = "Отвечает за работу со стеками")
@RequestMapping(value = "/api/v1/stack")
public class StackController {
    @Operation(
            summary = "Получить список стеков",
            description = "Позволяет получить список доступных стеков"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<StackDto> getStackList(
            @RequestParam(name = "query") @Parameter(description = "Название стека") String query
    ) {
        return null;
    }

    @Operation(summary = "Создать стек")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public StackDto createStack(
            @Valid @RequestBody CreateStackDto createUpdateStackDto
    ) {
        return null;
    }

    @Operation(summary = "Обновить стек")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{stackId}")
    public StackDto updateStack(
            @PathVariable @Parameter(description = "Идентификатор стека", required = true) UUID stackId,
            @Valid @RequestBody CreateStackDto createUpdateStackDto
    ) {
        return null;
    }

    @Operation(summary = "Удалить стек")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{stackId}")
    public Response deleteStack(
            @PathVariable @Parameter(description = "Идентификатор стека", required = true) UUID stackId
    ) {
        return null;
    }
}
