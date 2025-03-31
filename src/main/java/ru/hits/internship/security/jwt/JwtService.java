package ru.hits.internship.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.hits.internship.user.entity.UserEntity;
import ru.hits.internship.user.models.auth.TokenDto;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String accessSecret;

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public TokenDto generateAccessToken(UserEntity user) {
        String id = UUID.randomUUID().toString();

        Instant issuedDate = Instant.now();
        Date expiredDate = Date.from(issuedDate.plusMillis(lifetime.toMillis()));

        String token = Jwts.builder()
                .id(id)
                .subject(user.getEmail())
                .issuedAt(Date.from(issuedDate))
                .expiration(expiredDate)
                .signWith(getSigningKey())
                .claims(makeClaims(user))
                .compact();

        return new TokenDto(token, expiredDate);
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Map<String, Object> makeClaims(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        Set<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        claims.put("roles", roles);
        return claims;
    }
}