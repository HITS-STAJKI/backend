package ru.hits.internship.group.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto группы")
public class GroupDto {
    @Schema(description = "Идентификатор группы", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Номер группы", example = "972202")
    private String number;

    @ArraySchema(schema = @Schema(implementation = StudentDto.class))
    private Set<StudentDto> students;

    @Schema(description = "Число студентов, обучающихся в группе", example = "16")
    private int studentsCount;
}
