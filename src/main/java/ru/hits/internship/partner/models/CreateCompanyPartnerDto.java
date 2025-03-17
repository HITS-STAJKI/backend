package ru.hits.internship.partner.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(description = "dto для создания компаний партнеров")
public class CreateCompanyPartnerDto {
    @Schema(description = "Имя компании", example = "1Сники")
    @NotNull(message = "Имя должно быть заполнено")
    @NotBlank(message = "Имя должно быть заполнено")
    private String name;
    @Schema(description = "Информация о компании", example = "Крутые, вот контакты")
    @NotNull(message = "Описание должно быть заполнено")
    @NotBlank(message = "Описание должно быть заполнено")
    private String description;
    @Schema(description = "Идентификатор представителя компании в вузе", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    private UUID curatorId;
}
