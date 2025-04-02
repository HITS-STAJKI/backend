package ru.hits.internship.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.hits.internship.user.model.dto.auth.TokenDto;
import ru.hits.internship.user.model.entity.UserEntity;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String accessSecret;

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    public String getEmail(String token) {
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
}