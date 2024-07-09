package com.project.playit.Auth.Service;

import com.project.playit.Auth.DTO.AuthenticateRequest;
import com.project.playit.Auth.DTO.RegisterRequest;

import java.util.Map;

public interface AuthService {
    public Map<String, String> register(RegisterRequest request);

    public Map<String, String> authenticate(AuthenticateRequest request);
}
