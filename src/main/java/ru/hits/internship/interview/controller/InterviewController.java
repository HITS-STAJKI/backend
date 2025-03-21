package ru.hits.internship.interview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.hits.internship.interview.models.CreateInterviewDto;
import ru.hits.internship.interview.models.InterviewDto;
import ru.hits.internship.interview.models.UpdateInterviewDto;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Отборы", description = "Отвечает за работу с отборами")
@RequestMapping(value = "/api/v1/interview")
public class InterviewController {
    @Operation(description = "Создать отбор", summary = "Запрос доступен для студентов")
    //ID студента берется из токена
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping()
    public InterviewDto createInterview(@RequestBody CreateInterviewDto createInterviewDto) {
        return null;
    }

    @Operation(description = "Обновить отбор")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{interviewId}")
    public InterviewDto updateInterview(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId,
            @RequestBody UpdateInterviewDto updateInterviewDto
    ) {
        return null;
    }

    @Operation(description = "Удалить отбор")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{interviewId}")
    public void deleteInterview(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId
    ) {
    }

    @Operation(description = "Получить отбор")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{interviewId}")
    public InterviewDto getInterview(
            @PathVariable @Parameter(description = "Id отбора") UUID interviewId
    ) {
        return null;
    }

    @Operation(description = "Получить список отборов", summary = "Запрос доступен для деканата")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{studentId}/list")
    public List<InterviewDto> getInterviewList(
            @PathVariable @Parameter(description = "Id студента") UUID studentId,
            @RequestParam(name = "pageNumber") @Parameter(description = "Номер страницы; начинается с нуля", required = true) int pageNumber,
            @RequestParam(name = "pageSize") @Parameter(description = "Размер страницы", required = true) int pageSize
    ) {
        return null;
    }

    @Operation(description = "Получить список отборов", summary = "Запрос доступен для студентов")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public List<InterviewDto> getInterviewList(
            @RequestParam(name = "pageNumber") @Parameter(description = "Номер страницы; начинается с нуля", required = true) int pageNumber,
            @RequestParam(name = "pageSize") @Parameter(description = "Размер страницы", required = true) int pageSize
    ) {
        return null;
    }

}
