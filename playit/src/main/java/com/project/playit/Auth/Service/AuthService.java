package com.project.playit.Auth.Service;

import com.project.playit.Auth.Controller.AuthenticateRequest;
import com.project.playit.Auth.Controller.RegisterRequest;

public interface AuthService {
    public String register(RegisterRequest request);

    public String authenticate(AuthenticateRequest request);
}
