package ru.hits.internship.user.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.request.create.TeacherCreateDto;
import ru.hits.internship.user.model.dto.role.response.TeacherDto;

import java.util.UUID;

public interface TeacherService {
    TeacherDto createTeacher(TeacherCreateDto createDto);
    PagedListDto<TeacherDto> getAllTeachers(UUID userId, Pageable pageable);
}
