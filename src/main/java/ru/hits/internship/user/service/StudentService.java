package ru.hits.internship.user.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.filter.StudentFilter;
import ru.hits.internship.user.model.dto.role.request.create.StudentCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.ReturnFromAcademDto;
import ru.hits.internship.user.model.dto.role.request.edit.StudentEditDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;

import java.util.Set;
import java.util.UUID;

public interface StudentService {
    StudentDto updateStudent(UUID studentId, StudentEditDto editDto);

    StudentDto createStudent(UUID userId, StudentCreateDto createDto);

    StudentDto sendStudentToAcadem(UUID studentId);

    StudentDto returnStudentFromAcadem(UUID studentId, ReturnFromAcademDto returnDto);

    ByteArrayResource importStudentsFromExcel(MultipartFile file);

    ByteArrayResource exportStudentsToExcel(Set<UUID> studentIds);

    PagedListDto<StudentDto> getAllStudents(UUID userId, StudentFilter studentFilter, Pageable pageable);
}
