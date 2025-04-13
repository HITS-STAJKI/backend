package ru.hits.internship.user.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.StudentCreateDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;

import java.util.UUID;

public interface StudentService {
    StudentDto createStudent(UUID userId, StudentCreateDto createDto);
    PagedListDto<StudentDto> getAllStudents(UUID userId, Pageable pageable);
}
