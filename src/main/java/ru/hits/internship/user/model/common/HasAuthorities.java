package ru.hits.internship.user.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public interface HasAuthorities {
    Set<? extends IRole> getRoles();

    @JsonIgnore
    default Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(getRoles())
                .orElse(Collections.emptySet())
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getUserRole().name()))
                .toList();
    }
}
