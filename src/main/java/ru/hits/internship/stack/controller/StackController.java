package ru.hits.internship.stack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.common.models.Pagination.PagedListDto;
import ru.hits.internship.stack.models.CreateUpdateStackDto;
import ru.hits.internship.stack.models.StackDto;
import ru.hits.internship.stack.models.StackFilter;

@RestController
@Tag(name = "Стек", description = "Отвечает за работу со стеками")
@RequestMapping(value = "/api/v1/stack")
public class StackController {
    @Operation(
            summary = "Получить список стеков"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public PagedListDto<StackDto> getStackList(
            @Valid @ParameterObject StackFilter groupFilter,
            @ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable
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
    @PostMapping
    public StackDto createStack(
            @Valid @RequestBody CreateUpdateStackDto createUpdateStackDto
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
            @Valid @RequestBody CreateUpdateStackDto createUpdateStackDto
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
