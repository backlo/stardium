package com.bb.stardium.security.util;

import com.bb.stardium.security.domain.AuthenticationPlayer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final int ONE_DAY_PER_MILLISECOND = 1000 * 60 * 60 * 24;
    private static final int EXPIRE_DAY = 1;

    @Value("${secret.key}")
    private String key;

    @Value("${secret.issuer}")
    private String issuer;

    @Value("${secret.type}")
    private String type;

    public String generateToken(AuthenticationPlayer player) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", player.getUsername());
        claims.put("role", player.getRoles());

        return createToken(claims, String.valueOf(player.getId()));
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setHeaderParam("typ", type)
                .setIssuer(issuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DAY * ONE_DAY_PER_MILLISECOND))
                .signWith(SignatureAlgorithm.HS256, key.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    public String extractAuthorities(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token)
                .before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
