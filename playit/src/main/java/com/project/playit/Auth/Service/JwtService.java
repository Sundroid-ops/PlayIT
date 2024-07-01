package com.project.playit.Auth.Service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    public void generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

    public String extractUserName(String token);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}
