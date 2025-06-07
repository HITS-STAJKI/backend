package ru.hits.internship.stack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import ru.hits.internship.stack.models.UpdateStackDto;
import ru.hits.internship.stack.service.StackService;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Stack", description = "Отвечает за работу со стеками")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/stack")
public class StackController {

    private final StackService stackService;

    @Operation(
            summary = "Получить список стеков",
            description = "Позволяет получить список доступных стеков"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<StackDto> getStackList(
            @RequestParam(name = "query", required = false) @Parameter(description = "Название стека") String query
    ) {
        return stackService.getAllStacks(query);
    }

    @Operation(summary = "Создать стек")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize("hasAnyRole('DEAN', 'CURATOR', 'EDUCATIONAL_PROGRAM_LEAD')")
    public StackDto createStack(
            @Valid @RequestBody CreateStackDto createStackDto
    ) {
        return stackService.createStack(createStackDto);
    }

    @Operation(summary = "Обновить стек")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{stackId}")
    @PreAuthorize("hasAnyRole('DEAN', 'CURATOR', 'EDUCATIONAL_PROGRAM_LEAD')")
    public StackDto updateStack(
            @PathVariable @Parameter(description = "Идентификатор стека", required = true) UUID stackId,
            @Valid @RequestBody UpdateStackDto updateStackDto
    ) {
        return stackService.updateStack(stackId, updateStackDto);
    }

    @Operation(summary = "Удалить стек")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{stackId}")
    @PreAuthorize("hasAnyRole('DEAN', 'CURATOR', 'EDUCATIONAL_PROGRAM_LEAD')")
    public Response deleteStack(
            @PathVariable @Parameter(description = "Идентификатор стека", required = true) UUID stackId
    ) {
        stackService.deleteStack(stackId);

        return new Response("Стек успешно удален", HttpStatus.OK.value());
    }
}
