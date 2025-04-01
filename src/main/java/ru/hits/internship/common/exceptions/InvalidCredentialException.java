package ru.hits.internship.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException() {
        super("Invalid credentials");
    }

    public InvalidCredentialException(String message) {
        super(message);
    }
}