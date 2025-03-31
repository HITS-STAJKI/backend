package ru.hits.internship.user.models.auth;

import java.util.Date;

public record TokenDto(
        String token,
        Date expirationDate
) {}