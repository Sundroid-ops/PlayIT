package com.project.playit.Auth.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public void generateToken(UserDetails userDetails);
}
