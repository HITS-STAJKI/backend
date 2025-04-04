package ru.hits.internship.interview.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hits.internship.language.models.LanguageDto;
import ru.hits.internship.partner.models.ShortCompanyPartnerDto;
import ru.hits.internship.stack.models.StackDto;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
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
    private List<LanguageDto> languages;
    @NotNull
    private ShortCompanyPartnerDto companyPartner;
}
