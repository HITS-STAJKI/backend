package ru.hits.internship.group.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Обновление группы")
public class UpdateGroupDto {
    @Schema(description = "Номер группы", example = "972202")
    @NotBlank(message = "Номер группы должен быть заполнен")
    private String number;
}
