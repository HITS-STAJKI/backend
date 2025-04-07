package ru.hits.internship.interview.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto для обновления отбора")
public class UpdateInterviewDto {
    @Schema(description = "Идентификатор стека", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор стека должен быть заполнен")
    private UUID stackId;
    @Schema(description = "Идентификаторы языков", example = "[\"3ea42ea8-5258-4086-a43f-113ff89577a1\"]")
    @NotNull(message = "Идентификаторы языков должны быть заполнены")
    @NotEmpty(message = "Идентификаторы языков должны быть заполнены")
    private List<UUID> languageIds;
    @Schema(description = "Статус отбора", example = "PENDING")
    @NotNull(message = "Статус должен быть заполнен")
    private StatusEnum status;
}
