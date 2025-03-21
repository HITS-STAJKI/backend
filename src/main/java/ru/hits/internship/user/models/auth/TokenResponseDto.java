package ru.hits.internship.user.models.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Schema(description = "Модель с токенами")
public record TokenResponseDto(
        @NotNull
        String accessToken,

        @NotNull
        String refreshToken,

        @NotNull
        Instant accessTokenExpirationDate,

        @NotNull
        Instant refreshExpiredDateTime
) {}
