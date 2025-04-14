package ru.hits.internship.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.group.repository.GroupRepository;
import ru.hits.internship.user.mapper.StudentMapper;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.request.create.StudentCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.StudentEditDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.repository.StudentRepository;
import ru.hits.internship.user.repository.UserRepository;
import ru.hits.internship.user.service.StudentService;
import ru.hits.internship.user.utils.RoleChecker;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    @Override
    public PagedListDto<StudentDto> getAllStudents(UUID userId, Pageable pageable) {
        return studentRepository.findAll(userId, pageable, StudentMapper.INSTANCE::toDto);
    }

    @Override
    public StudentDto createStudent(UUID userId, StudentCreateDto createDto) {
        GroupEntity group = groupRepository.findById(createDto.groupId())
                .orElseThrow(() -> new NotFoundException(GroupEntity.class, createDto.groupId()));

        UserEntity user = userRepository.findByIdOrThrow(userId);
        RoleChecker.verifyRoleAvailable(user, UserRole.STUDENT);

        StudentEntity student = StudentMapper.INSTANCE.toEntity(user, group);
        studentRepository.save(student);

        return StudentMapper.INSTANCE.toDto(student);
    }

    @Override
    public StudentDto updateStudent(UUID studentId, StudentEditDto editDto) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(StudentEntity.class, studentId));

        GroupEntity group = groupRepository.findById(editDto.groupId())
                .orElseThrow(() -> new NotFoundException(GroupEntity.class, editDto.groupId()));

        StudentEntity updatedStudent = StudentMapper.INSTANCE.updateStudent(student, group);
        studentRepository.save(updatedStudent);

        return StudentMapper.INSTANCE.toDto(updatedStudent);
    }
}