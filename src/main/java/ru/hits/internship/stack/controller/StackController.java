package ru.hits.internship.stack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.stack.models.CreateUpdateStackDto;
import ru.hits.internship.stack.models.StackDto;

import java.util.List;

@RestController
@Tag(name = "Стек", description = "Отвечает за работу со стеками")
@RequestMapping(value = "/api/v1/stack")
public class StackController {
    @Operation(
            summary = "Получить список стеков"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<StackDto> getStackList(
            @RequestParam(name = "pageNumber") @Parameter(description = "Номер страницы; начинается с нуля", required = true) int pageNumber,
            @RequestParam(name = "pageSize") @Parameter(description = "Размер страницы", required = true) int pageSize,
            @RequestParam(name = "query") @Parameter(description = "Название стека", required = false) String query
    ) {
        return null;
    }

    @Operation(
            summary = "Получить информацию о стеке"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{stackId}")
    public StackDto getStack(
            @PathVariable @Parameter(description = "Идентификатор стека", required = true) Long stackId
    ) {
        return null;
    }

    @Operation(
            summary = "Создать стек"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping()
    public StackDto createStack(
            @RequestBody CreateUpdateStackDto createUpdateStackDto
    ) {
        return null;
    }

    @Operation(
            summary = "Обновить стек"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{stackId}")
    public StackDto updateStack(
            @PathVariable @Parameter(description = "Идентификатор стека", required = true) Long stackId,
            @RequestBody CreateUpdateStackDto createUpdateStackDto
    ) {
        return null;
    }

    @Operation(
            summary = "Удалить стек"
    )
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{stackId}")
    public void deleteStack(
            @PathVariable @Parameter(description = "Идентификатор стека", required = true) Long stackId
    ) {
    }
}
