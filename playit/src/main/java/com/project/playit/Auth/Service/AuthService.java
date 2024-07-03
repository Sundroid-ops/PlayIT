package com.project.playit.Auth.Service;

import com.project.playit.Auth.DTO.AuthenticateRequest;
import com.project.playit.Auth.DTO.RegisterRequest;

public interface AuthService {
    public String register(RegisterRequest request);

    public String authenticate(AuthenticateRequest request);
}
