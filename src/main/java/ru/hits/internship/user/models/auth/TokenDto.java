package ru.hits.internship.user.models.auth;

import java.time.LocalDateTime;

public record TokenDto(
        String token,
        LocalDateTime expirationDate
) {
}