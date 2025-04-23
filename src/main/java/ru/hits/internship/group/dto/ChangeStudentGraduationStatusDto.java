package ru.hits.internship.group.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStudentGraduationStatusDto {
    @Schema(description = "Идентификатор студента")
    @NotNull
    private UUID studentId;
    @Schema(description = "Статус выпуска студента", example = "false")
    @NotNull
    private Boolean status;
}
