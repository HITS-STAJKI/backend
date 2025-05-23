package ru.hits.internship.statistics.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.statistics.dto.StatisticsResponse;
import ru.hits.internship.statistics.dto.StudentFilter;
import ru.hits.internship.statistics.service.StatisticsService;

@RestController
@Validated
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@Tag(name = "Statistics", description = "Отвечает за работу со статистикой")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Operation(summary = "Получить количество студентов по фильтру", description = "Получить число студентов, удовлетворяющих заданным условиям")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('DEAN', 'EDUCATIONAL_PROGRAM_LEAD')")
    @GetMapping("/students/count")
    public StatisticsResponse countStudentsByFilter(
            @Valid @ParameterObject @Parameter(description = "Фильтр для студентов")
            StudentFilter studentFilter) {
        return statisticsService.countStudentsByFilter(studentFilter);
    }
}
