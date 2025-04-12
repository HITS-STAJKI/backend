package ru.hits.internship.partner.models;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Фильтр для получения страницы компаний-партнеров")
public class PartnerFilter {
    @Schema(description = "Идентификатор компании", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Название компании (разрешается частичное совпадение)", example = "NTR")
    private String name;

    @Schema(description = "Стеки компании (список id стеков); отображаются по принципу объединения", example = "[\"123e4567-e89b-12d3-a456-426614174000\", \"123e4567-e89b-12d3-a456-426614174001\"]")
    private List<UUID> stacks;

    @Schema(description = "Только новые компании", example = "true")
    private Boolean isNew;
}
