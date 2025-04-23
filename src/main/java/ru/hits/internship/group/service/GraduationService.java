package ru.hits.internship.group.service;

import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.group.dto.ChangeStudentGraduationStatusDto;

import java.util.UUID;

public interface GraduationService {
    Response graduateGroup(UUID groupId);

    Response changeStudentGraduationStatus(ChangeStudentGraduationStatusDto dto);
}
