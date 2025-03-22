package ru.hits.internship.group.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Фильтр для получения страницы групп")
public class GroupFilter {
    @Schema(description = "Идентификатор группы", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Номер группы (разрешается частичное совпадение)", example = "972202")
    private String number;
}
