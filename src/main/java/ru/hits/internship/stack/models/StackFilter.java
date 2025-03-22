package ru.hits.internship.stack.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Фильтр для получения страницы стеков")
public class StackFilter {
    @Schema(description = "Идентификатор стека", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Название стека (разрешается частичное совпадение)", example = "Java")
    private String name;
}
