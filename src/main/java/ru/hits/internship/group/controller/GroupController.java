package ru.hits.internship.group.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.group.dto.CreateGroupDto;
import ru.hits.internship.group.dto.GroupDto;
import ru.hits.internship.group.dto.GroupFilter;
import ru.hits.internship.group.dto.UpdateGroupDto;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/v1/groups")
@Tag(name = "Группы", description = "Отвечает за работу с группами")
public class GroupController {

    @PostMapping
    @Operation(
            summary = "Создание группы",
            description = "Позволяет создать группу, указав её номер",
            security = {
                    @SecurityRequirement(name = "bearerAuth", scopes = {"ROLE_DEAN"})
            })
    public GroupDto createGroup(@Valid @RequestBody CreateGroupDto createGroupDto) {
        return null;
    }

    @GetMapping
    @Operation(
            summary = "Получение страницы групп по фильтру",
            description = "Позволяет получить страницу групп по фильтру (список может иметь разное число элементов)",
            security = {
                    @SecurityRequirement(name = "bearerAuth", scopes = {"ROLE_DEAN"})
            })
    public Page<GroupDto> getGroups(@Valid @ParameterObject GroupFilter groupFilter,
                                    @ParameterObject @PageableDefault(sort = "number",
                                            direction = Sort.Direction.ASC) Pageable pageable) {
        return null;
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Обновление группы",
            description = "Позволяет обновить номер группы",
            security = {
                    @SecurityRequirement(name = "bearerAuth", scopes = {"ROLE_DEAN"})
            })
    public GroupDto updateGroup(@PathVariable @Parameter(name = "id группы") UUID id,
                                @Valid @RequestBody UpdateGroupDto updateGroupDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление группы",
            description = "Позволяет удалить группу",
            security = {
                    @SecurityRequirement(name = "bearerAuth", scopes = {"ROLE_DEAN"})
            })
    public void deleteGroup(@PathVariable @Parameter(name = "id группы") UUID id) {
    }
}
