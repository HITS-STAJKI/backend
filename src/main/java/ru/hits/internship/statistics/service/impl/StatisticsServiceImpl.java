package ru.hits.internship.statistics.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.statistics.dto.StatisticsResponse;
import ru.hits.internship.statistics.dto.StudentFilter;
import ru.hits.internship.statistics.service.StatisticsService;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.repository.StudentRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final StudentRepository studentRepository;
    private final List<Filter<StudentEntity, StudentFilter>> filters;

    @Override
    public StatisticsResponse countStudentsByFilter(StudentFilter studentFilter) {
        Specification<StudentEntity> specification = Optional.ofNullable(studentFilter)
                .map(f -> filters.stream()
                        .map(filter -> filter.build(f))
                        .filter(Objects::nonNull)
                        .reduce(Specification.where(null), Specification::and))
                .orElse(Specification.where(null));

        return new StatisticsResponse(studentRepository.count(specification));
    }
}
