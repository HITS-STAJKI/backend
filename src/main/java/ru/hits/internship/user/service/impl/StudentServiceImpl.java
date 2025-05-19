package ru.hits.internship.user.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
import ru.hits.internship.practice.repository.PracticeRepository;
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
import ru.hits.internship.user.utils.PasswordGenerator;
import ru.hits.internship.user.utils.RoleChecker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private final PracticeRepository practiceRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${export.student-batch-size:100}")
    private int batchSize;

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
    public ByteArrayResource importStudentsFromExcel(MultipartFile file) {
        List<String[]> resultRows = new ArrayList<>();

        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String fullName = row.getCell(0).getStringCellValue().trim();
                String groupNumber = row.getCell(1).getStringCellValue().trim();
                String email = row.getCell(2).getStringCellValue().trim();

                String rawPassword = PasswordGenerator.generateBasedOn(email);
                String encodedPassword = passwordEncoder.encode(rawPassword);

                UserEntity user = userRepository.findByEmail(email).orElse(null);

                if (user == null) {
                    user = new UserEntity();
                    user.setFullName(fullName);
                    user.setEmail(email);
                    user.setPassword(encodedPassword);
                    user = userRepository.save(user);
                }

                boolean alreadyStudent = user.getRoles().stream()
                        .anyMatch(role -> role instanceof StudentEntity);

                if (!alreadyStudent) {
                    GroupEntity group = groupRepository.findByNumberIgnoreCase(groupNumber)
                            .orElseThrow(() -> new NotFoundException("Группа не найдена: " + groupNumber));

                    StudentEntity student = new StudentEntity();
                    student.setUser(user);
                    student.setGroup(group);
                    studentRepository.save(student);
                }

                resultRows.add(new String[]{fullName, email, rawPassword});
            }

            Workbook resultWorkbook = new XSSFWorkbook();
            Sheet resultSheet = resultWorkbook.createSheet("Результат импорта");

            Row header = resultSheet.createRow(0);
            header.createCell(0).setCellValue("ФИО");
            header.createCell(1).setCellValue("Почта");
            header.createCell(2).setCellValue("Сгенерированный пароль");

            for (int i = 0; i < resultRows.size(); i++) {
                Row row = resultSheet.createRow(i + 1);
                String[] data = resultRows.get(i);
                row.createCell(0).setCellValue(data[0]);
                row.createCell(1).setCellValue(data[1]);
                row.createCell(2).setCellValue(data[2]);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            resultWorkbook.write(out);
            return new ByteArrayResource(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при обработке Excel", e);
        }
    }


    public ByteArrayResource exportStudentsToExcel(List<UUID> userIds) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Студенты");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ФИО");
        header.createCell(1).setCellValue("Поток");
        header.createCell(2).setCellValue("Компания");

        int rowIndex = 1;
        int page = 0;

        Page<StudentEntity> batch;
        do {
            Pageable pageable = PageRequest.of(page++, batchSize);
            batch = userIds == null || userIds.isEmpty()
                    ? studentRepository.findAll(pageable)
                    : studentRepository.findAllByUserIdIn(userIds, pageable);

            for (StudentEntity student : batch.getContent()) {
                Row row = sheet.createRow(rowIndex++);
                UserEntity user = student.getUser();

                String groupNumber = student.getGroup() != null ? student.getGroup().getNumber() : "";
                String companyName = practiceRepository.findByStudentIdAndIsArchivedFalse(student.getId())
                        .map(p -> p.getCompany().getName())
                        .orElse("");

                row.createCell(0).setCellValue(user.getFullName());
                row.createCell(1).setCellValue(groupNumber);
                row.createCell(2).setCellValue(companyName);
            }
        } while (batch.hasNext());

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayResource(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании Excel", e);
        }
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