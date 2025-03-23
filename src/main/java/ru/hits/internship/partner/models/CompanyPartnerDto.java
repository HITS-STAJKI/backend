package ru.hits.internship.partner.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hits.internship.user.models.user.UserDto;

@Data
@AllArgsConstructor
@Schema(description = "dto для компаний партнеров")
public class CompanyPartnerDto {
    @Schema(description = "Идентификатор языка", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    @NotNull(message = "Идентификатор должен быть заполнен")
    @NotBlank(message = "Идентификатор должен быть заполнен")
    private String id;
    @Schema(description = "Имя компании", example = "1Сники")
    @NotNull(message = "Имя должно быть заполнено")
    @NotBlank(message = "Имя должно быть заполнено")
    private String name;
    @Schema(description = "Информация о компании", example = "Крутые, вот контакты")
    @NotNull(message = "Описание должно быть заполнено")
    @NotBlank(message = "Описание должно быть заполнено")
    private String description;
    @Schema(description = "Куратор, закрепленный за компанией")
    private UserDto curator;
}
