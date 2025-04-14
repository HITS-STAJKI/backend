package ru.hits.internship.user.model.dto.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.role.response.RoleDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public record AuthUser(
        UUID id,
        String email,
        String firstName,
        String lastName,
        Map<UserRole, RoleDto> roles
) {
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(roles)
                .orElse(Collections.emptyMap())
                .keySet()
                .stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.name()))
                .toList();
    }
}