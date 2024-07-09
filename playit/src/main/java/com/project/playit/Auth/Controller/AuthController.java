package com.project.playit.Auth.Controller;

import com.project.playit.Auth.DTO.AuthenticateRequest;
import com.project.playit.Auth.DTO.RegisterRequest;
import com.project.playit.Auth.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Map<String, String> register(@Valid @RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/authenticate")
    public Map<String, String> authenticate(@Valid @RequestBody AuthenticateRequest request) throws UsernameNotFoundException{
        return authService.authenticate(request);
    }
}