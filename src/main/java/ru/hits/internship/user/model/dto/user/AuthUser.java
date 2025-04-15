package ru.hits.internship.user.model.dto.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.hits.internship.user.model.dto.role.response.RoleDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public record AuthUser (
        UUID id,
        String email,
        String fullName,
        Set<RoleDto> roles
) {
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(roles)
                .orElse(Collections.emptySet())
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.userRole().name()))
                .toList();
    }
}