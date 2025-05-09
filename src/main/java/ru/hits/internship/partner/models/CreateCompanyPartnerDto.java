package ru.hits.internship.partner.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Schema(description = "Идентификатор файла-логотипа")
    private UUID fileId;
}
