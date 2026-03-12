package com.tanu.jobportal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key (generated once when app starts)
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    // ================= GENERATE TOKEN =================
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)                 // store email
                .claim("role", role)               // store role
                .setIssuedAt(new Date())           // current time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hrs
                .signWith(key, SignatureAlgorithm.HS256)  // VERY IMPORTANT
                .compact();
    }


    // ================= EXTRACT EMAIL =================
    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }


    // ================= EXTRACT ROLE =================
    public String extractRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("role", String.class);
    }


    // ================= VALIDATE TOKEN =================
    public boolean validateToken(String token, String email) {
        String extractedEmail = extractEmail(token);
        return extractedEmail.equals(email);
    }
}