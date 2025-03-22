package ru.hits.internship.interview.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.internship.partner.models.CompanyPartnerShortDto;
import ru.hits.internship.stack.models.StackDto;
import ru.hits.internship.user.models.role.StudentDto;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto отбора")
public class InterviewDto {
    @Schema(description = "Идентификатор отбора", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор должен быть заполнен")
    @NotBlank(message = "Идентификатор должен быть заполнен")
    private UUID id;
    @Schema(description = "Статус отбора", example = "PENDING")
    @NotNull(message = "Статус должен быть заполнен")
    private StatusEnum status;
    @NotNull
    private StackDto stack;
    @NotNull
    private StudentDto student;
    @NotNull
    private CompanyPartnerShortDto companyPartner;
}
