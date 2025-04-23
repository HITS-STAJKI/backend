package ru.hits.internship.group.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.group.dto.ChangeStudentGraduationStatusDto;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.group.repository.GroupRepository;
import ru.hits.internship.group.service.GraduationService;
import ru.hits.internship.practice.entity.PracticeEntity;
import ru.hits.internship.practice.repository.PracticeRepository;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.repository.StudentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GraduationServiceImpl implements GraduationService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final PracticeRepository practiceRepository;

    @Override
    @Transactional
    public Response graduateGroup(UUID groupId) {
        groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(GroupEntity.class, groupId));

        int updatedStudents = studentRepository.graduateStudentsByGroup(groupId);
        int updatedPractices = practiceRepository.approvePracticesByGroup(groupId);

        return new Response(
                String.format("Выпущено %d студентов и одобрено %d практик",
                        updatedStudents, updatedPractices),
                HttpStatus.OK.value()
        );
    }

    @Override
    @Transactional
    public Response changeStudentGraduationStatus(ChangeStudentGraduationStatusDto dto) {
        var studentId = dto.getStudentId();
        var status = dto.getStatus();

        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(StudentEntity.class, studentId));

        if (status && !student.getIsGraduated()) {
            student.setIsGraduated(true);
            studentRepository.save(student);

            Optional<PracticeEntity> practiceOptional = practiceRepository.findByStudentIdAndIsArchivedFalse(studentId);

            if (practiceOptional.isPresent()) {
                var practice = practiceOptional.get();

                practice.setIsArchived(true);
                practiceRepository.save(practice);
            }
        } else if (!status && student.getIsGraduated()) {
            student.setIsGraduated(false);
            studentRepository.save(student);
        }

        return new Response(
                "Статус выпуска студента успешно изменен",
                HttpStatus.OK.value()
        );
    }
}
