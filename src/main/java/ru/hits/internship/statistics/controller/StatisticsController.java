package ru.hits.internship.statistics.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/statistics")
@Tag(name = "Statistics", description = "Отвечает за работу со статистикой")
public class StatisticsController {
}
