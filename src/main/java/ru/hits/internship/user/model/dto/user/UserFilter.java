package ru.hits.internship.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.internship.user.model.common.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Фильтр для получения страницы пользователей")
public class UserFilter {
    @Schema(description = "ФИО пользователя (разрешается частичное совпадение)", example = "Иванов Иван Иванович")
    private String fullName;

    @Schema(description = "Роль пользователя")
    private UserRole userRole;
}
