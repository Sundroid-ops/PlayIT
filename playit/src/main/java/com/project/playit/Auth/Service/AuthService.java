package com.project.playit.Auth.Service;

import com.project.playit.Auth.Controller.AuthenticateRequest;
import com.project.playit.Auth.Controller.RegisterRequest;

public interface AuthService {
    public void register(RegisterRequest request);

    public void authenticate(AuthenticateRequest request);
}
