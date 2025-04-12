package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.hits.internship.partner.models.ShortCompanyPartnerDto;
import ru.hits.internship.user.model.dto.user.UserShortDto;

import java.util.UUID;

@Schema(description = "Модель куратора")
public record CuratorDto(
        @NotNull
        @Schema(description = "Идентификатор роли")
        UUID id,

        @NotNull
        UserShortDto user,

        @NotNull
        ShortCompanyPartnerDto company
) {}