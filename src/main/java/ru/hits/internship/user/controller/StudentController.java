package ru.hits.internship.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.filter.StudentFilter;
import ru.hits.internship.user.model.dto.role.request.create.StudentCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.ReturnFromAcademDto;
import ru.hits.internship.user.model.dto.role.request.edit.StudentEditDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import ru.hits.internship.user.service.StudentService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@Tag(name = "Student", description = "Отвечает за работу со студентами")
@RequestMapping(value = "/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Создание студента для текущего пользователя")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public StudentDto createStudentForCurrentUser(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody @Valid StudentCreateDto createDto
    ) {
        return studentService.createStudent(authUser.id(), createDto);
    }

    @Operation(summary = "Создание студента")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PostMapping("user/{userId}")
    public StudentDto createStudent(
            @Schema(description = "ID пользователя")
            @PathVariable UUID userId,
            @RequestBody @Valid StudentCreateDto createDto
    ) {
        return studentService.createStudent(userId, createDto);
    }

    @Operation(summary = "Обновление информации о студенте")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PutMapping("/{id}")
    public StudentDto updateStudent(
            @Schema(description = "ID студента")
            @PathVariable UUID id,
            @RequestBody @Valid StudentEditDto editDto
    ) {
        return studentService.updateStudent(id, editDto);
    }

    @Operation(summary = "Получение всех студентов")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('DEAN', 'EDUCATIONAL_PROGRAM_LEAD')")
    @GetMapping("/list")
    public PagedListDto<StudentDto> getAllStudents(
            @AuthenticationPrincipal AuthUser authUser,
            @ParameterObject Pageable pageable,
            @ParameterObject StudentFilter studentFilter
    ) {
        return studentService.getAllStudents(authUser.id(), studentFilter, pageable);
    }

    @Operation(summary = "Отправка студента в академ")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PutMapping("/{studentId}/to-academ")
    public StudentDto sendStudentToAcadem(
            @Schema(description = "ID студента")
            @PathVariable UUID studentId
    ) {
        return studentService.sendStudentToAcadem(studentId);
    }

    @Operation(summary = "Возвращение студента из академа")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PutMapping("/{studentId}/from-academ")
    public StudentDto returnStudentFromAcadem(
            @Schema(description = "ID студента")
            @PathVariable UUID studentId,
            @RequestBody @Valid ReturnFromAcademDto returnDto
    ) {
        return studentService.returnStudentFromAcadem(studentId, returnDto);
    }

    @Operation(summary = "Импорт студентов из Excel-файла", description = "Формат таблицы: Полное имя - номер потока - электронная почта")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ByteArrayResource> importStudents(@RequestParam("file") MultipartFile file) {
        ByteArrayResource resource = studentService.importStudentsFromExcel(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=import_result.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    @Operation(summary = "Экспорт студентов в Excel-файл")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('DEAN')")
    @GetMapping("/export")
    public ResponseEntity<ByteArrayResource> exportStudents(
            @RequestParam(required = false) Set<UUID> studentIds
    ) {
        ByteArrayResource resource = studentService.exportStudentsToExcel(studentIds);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    @Operation(summary = "Получение информации о студентах по id")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('DEAN', 'EDUCATIONAL_PROGRAM_LEAD')")
    @PostMapping("/list/ids")
    public List<StudentDto> getStudentsByIds(
            @RequestBody Set<UUID> studentIds
    ) {
        return studentService.getStudentsByIds(studentIds);
    }
}
