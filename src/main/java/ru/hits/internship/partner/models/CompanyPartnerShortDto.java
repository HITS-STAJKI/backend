package ru.hits.internship.partner.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto краткой информации о компании партнере")
public class CompanyPartnerShortDto {
    @Schema(description = "Идентификатор компании партнера", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор компании должен быть заполнен")
    @NotBlank(message = "Идентификатор компании должен быть заполнен")
    private String id;
    @Schema(description = "Имя компании", example = "1Сники")
    @NotNull(message = "Имя должно быть заполнено")
    @NotBlank(message = "Имя должно быть заполнено")
    private String name;
    @Schema(description = "Идентификатор куратора", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор куратора должен быть заполнен")
    @NotBlank(message = "Идентификатор куратора должен быть заполнен")
    private String curatorId;
}
