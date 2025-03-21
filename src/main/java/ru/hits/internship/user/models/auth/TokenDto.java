package ru.hits.internship.user.models.auth;

import java.time.Instant;

public record TokenDto(
        String value,
        Instant expirationDate
) {}