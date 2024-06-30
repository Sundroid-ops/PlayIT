package com.project.playit.Auth.Service;

import com.project.playit.Auth.Controller.RegisterRequest;
import com.project.playit.Auth.Entity.User.User;
import com.project.playit.Auth.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    public void register(RegisterRequest request){
        User user = User.builder()
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();


    }
}
