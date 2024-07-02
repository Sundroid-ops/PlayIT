package com.project.playit.Auth.Controller;

import com.project.playit.Auth.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/authenticate")
    public String authenticate(@Valid @RequestBody AuthenticateRequest request){
        return authService.authenticate(request);
    }
}