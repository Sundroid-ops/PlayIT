package com.project.playit.Auth.Service;

import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;

public interface JwtService {
    public void generateToken(UserDetails userDetails);

}
