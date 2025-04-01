package ru.hits.internship.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.hits.internship.user.service.UserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.substring(TOKEN_PREFIX.length());
            String email = jwtService.getEmail(token);
            authenticateIfPossible(email);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private void authenticateIfPossible(String email) {
        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        var userDto = userService.getUserByEmail(email);
        var authToken = new UsernamePasswordAuthenticationToken(userDto, null, userDto.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
