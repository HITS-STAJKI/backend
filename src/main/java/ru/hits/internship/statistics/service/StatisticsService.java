package ru.hits.internship.statistics.service;

import ru.hits.internship.statistics.dto.StatisticsResponse;
import ru.hits.internship.statistics.dto.StudentFilter;

public interface StatisticsService {
    StatisticsResponse countStudentsByFilter(StudentFilter studentFilter);
}
