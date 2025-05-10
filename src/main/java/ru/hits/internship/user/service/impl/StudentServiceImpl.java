package ru.hits.internship.user.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hits.internship.chat.entity.ChatReadStateEntity;
import ru.hits.internship.chat.model.chat.ChatDto;
import ru.hits.internship.chat.repository.ChatReadStateRepository;
import ru.hits.internship.chat.repository.MessageRepository;
import ru.hits.internship.chat.service.ChatService;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.group.repository.GroupRepository;
import ru.hits.internship.user.mapper.StudentMapper;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.request.create.StudentCreateDto;
import ru.hits.internship.user.model.dto.role.request.edit.ReturnFromAcademDto;
import ru.hits.internship.user.model.dto.role.request.edit.StudentEditDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.repository.StudentRepository;
import ru.hits.internship.user.repository.UserRepository;
import ru.hits.internship.user.service.StudentService;
import ru.hits.internship.user.utils.RoleChecker;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final ChatService chatService;
    private final ChatReadStateRepository chatReadStateRepository;
    private final MessageRepository messageRepository;

    @Override
    public PagedListDto<StudentDto> getAllStudents(UUID userId, String fullName, Pageable pageable) {
        //return studentRepository.findAll(userId, fullName, pageable, StudentMapper.INSTANCE::toDto);
        return studentRepository.findAll(null, fullName, pageable, studentEntity -> {
            StudentDto studentDto = StudentMapper.INSTANCE.toDto(studentEntity);

            if (studentEntity.getChat() != null) {
                UUID chatId = studentEntity.getChat().getId();
                ChatReadStateEntity chatReadState = chatReadStateRepository
                        .findByChatIdAndUserId(chatId, userId)
                        .orElse(null);
                LocalDateTime lastReadTime = (chatReadState != null) ? chatReadState.getLastReadAt() : null;

                long unreadCount;

                if (lastReadTime != null) {
                    unreadCount = messageRepository.countUnread(chatId, userId, lastReadTime);
                } else {
                    unreadCount = messageRepository.countAllByChat_IdAndSender_IdNot(chatId, userId);
                }
                studentDto = StudentMapper.INSTANCE.toDtoWithUnreadCount(studentEntity, unreadCount);
            } else {
                studentDto = StudentMapper.INSTANCE.toDtoWithUnreadCount(studentEntity, 0L);
            }
            return studentDto;
        });
    }

    @Override
    @Transactional
    public StudentDto createStudent(UUID userId, StudentCreateDto createDto) {
        GroupEntity group = groupRepository.findById(createDto.groupId())
                .orElseThrow(() -> new NotFoundException(GroupEntity.class, createDto.groupId()));

        UserEntity user = userRepository.findByIdOrThrow(userId);
        RoleChecker.verifyRoleAvailable(user, UserRole.STUDENT);

        StudentEntity student = StudentMapper.INSTANCE.toEntity(user, group);
        studentRepository.save(student);

        chatService.createChat(student.getId());

        return StudentMapper.INSTANCE.toDto(student);
    }

    @Override
    @Transactional
    public StudentDto sendStudentToAcadem(UUID studentId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(StudentEntity.class, studentId));

        student.setIsAcadem(true);
        student.setGroup(null);
        studentRepository.save(student);

        return StudentMapper.INSTANCE.toDto(student);
    }

    @Override
    @Transactional
    public StudentDto returnStudentFromAcadem(UUID studentId, ReturnFromAcademDto returnDto) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(StudentEntity.class, studentId));

        GroupEntity group = groupRepository.findById(returnDto.groupId())
                .orElseThrow(() -> new NotFoundException(GroupEntity.class, returnDto.groupId()));

        student.setIsAcadem(false);
        StudentEntity updatedStudent = StudentMapper.INSTANCE.updateStudent(student, group);
        studentRepository.save(updatedStudent);

        return StudentMapper.INSTANCE.toDto(updatedStudent);
    }

    @Override
    @Transactional
    public StudentDto updateStudent(UUID studentId, StudentEditDto editDto) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(StudentEntity.class, studentId));

        GroupEntity group = groupRepository.findById(editDto.groupId())
                .orElseThrow(() -> new NotFoundException(GroupEntity.class, editDto.groupId()));

        if (Boolean.TRUE.equals(student.getIsAcadem())) {
            throw new BadRequestException("Cannot change group while student is on academic leave.");
        }

        StudentEntity updatedStudent = StudentMapper.INSTANCE.updateStudent(student, group);
        studentRepository.save(updatedStudent);

        return StudentMapper.INSTANCE.toDto(updatedStudent);
    }
}