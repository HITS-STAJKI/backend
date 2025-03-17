package ru.hits.internship.exceptions.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Schema(description = "dto для возвращения ошибочных результатов")
public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private Integer status;
    private Set<ValidationErrorResponse> validationErrors;

    public ErrorResponse(String message, Integer status) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public ErrorResponse(String message, Integer status, List<ValidationErrorResponse> validationErrors) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.validationErrors = Set.copyOf(validationErrors);
    }
}
