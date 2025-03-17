package ru.hits.internship.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;
}
