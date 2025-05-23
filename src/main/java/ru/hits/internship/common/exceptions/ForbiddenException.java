package ru.hits.internship.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("У пользователя нет прав доступа");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
