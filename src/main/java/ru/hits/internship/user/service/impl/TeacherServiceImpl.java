package ru.hits.internship.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.mapper.TeacherMapper;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.request.create.TeacherCreateDto;
import ru.hits.internship.user.model.dto.role.response.TeacherDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.TeacherEntity;
import ru.hits.internship.user.repository.TeacherRepository;
import ru.hits.internship.user.repository.UserRepository;
import ru.hits.internship.user.service.TeacherService;
import ru.hits.internship.user.utils.RoleChecker;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public PagedListDto<TeacherDto> getAllTeachers(UUID userId, Pageable pageable) {
        return teacherRepository.findAll(userId, pageable, TeacherMapper.INSTANCE::toDto);
    }

    @Override
    public TeacherDto createTeacher(TeacherCreateDto createDto) {
        UserEntity user = userRepository.findByIdOrThrow(createDto.userId());
        RoleChecker.verifyRoleAvailable(user, UserRole.TEACHER);

        TeacherEntity teacher = TeacherMapper.INSTANCE.toEntity(user);
        teacherRepository.save(teacher);

        return TeacherMapper.INSTANCE.toDto(teacher);
    }
}
