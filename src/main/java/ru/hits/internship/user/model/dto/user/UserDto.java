package ru.hits.internship.user.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.hits.internship.user.model.dto.role.response.RoleDto;

import java.util.*;

@Schema(description = "Модель пользователя")
public record UserDto (
        @Schema(description = "Идентификатор пользователя")
        UUID id,

        @Schema(description = "Адрес электронной почты", example = "example@example.ru")
        String email,

        @Schema(description = "Имя пользователя", example = "Иван")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Иванов")
        String lastName,

        @ArraySchema(schema = @Schema(implementation = RoleDto.class, description = "Роль пользователя"))
        Set<RoleDto> roles
) {
        @JsonIgnore
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return Optional.ofNullable(roles)
                        .orElse(Collections.emptySet())
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.userRole().name()))
                        .toList();
        }
}