package com.project.playit.Auth.Service;

import com.project.playit.Auth.Controller.RegisterRequest;
import com.project.playit.Auth.Entity.User.Role;
import com.project.playit.Auth.Entity.User.User;
import com.project.playit.Auth.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request){
        User user = User.builder()
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }
}
