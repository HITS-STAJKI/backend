package ru.hits.internship.user.model.dto.auth;

import java.util.Date;

public record TokenDto(
        String token,
        Date expirationDate
) {}