package ru.hits.internship.practice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hits.internship.group.dto.GroupShortDto;
import ru.hits.internship.partner.models.CompanyPartnerDto;
import ru.hits.internship.stack.models.StackDto;
import ru.hits.internship.user.model.dto.user.UserDto;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@Schema(description = "dto для практик")
public class PracticeDto {
    @Schema(description = "Идентификатор практики", example = "3ea42ea8-5258-4086-a43f-113ff89577a1")
    private UUID id;
    @Schema(description = "Владелец практики")
    private UserDto user;
    @Schema(description = "Группа владельца практики")
    private GroupShortDto group;
    @Schema(description = "Место прохождения практики")
    private CompanyPartnerDto company;
    @Schema(description = "Стек практики")
    private StackDto stack;
    @Schema(description = "Время создания практики")
    private LocalDateTime createdAt;
    @Schema(description = "Статус оплачиваемости", example = "true")
    private Boolean isPaid;
    @Schema(description = "Является ли заархивированной", example = "true")
    private Boolean isArchived;
    @Schema(description = "Подтверждена куратором", example = "true")
    private Boolean isApproved;
}
