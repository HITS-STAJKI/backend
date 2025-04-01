package ru.hits.internship.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> entity, String id) {
        super(String.format("Entity %s (%s) not found", entity.getSimpleName(), id));
    }
}
