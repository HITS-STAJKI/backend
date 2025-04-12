package ru.hits.internship.user.model.dto.role.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.hits.internship.partner.models.ShortCompanyPartnerDto;

import java.util.UUID;

@Schema(description = "Модель куратора")
public record CuratorShortDto(
        @NotNull
        @Schema(description = "Идентификатор роли")
        UUID id,

        @NotNull
        ShortCompanyPartnerDto company
) {}
