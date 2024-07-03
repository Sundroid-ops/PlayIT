package com.project.playit.Auth.Service;

import com.project.playit.Auth.DTO.AuthenticateRequest;
import com.project.playit.Auth.DTO.RegisterRequest;
import com.project.playit.Auth.Entity.User.Role;
import com.project.playit.Auth.Entity.User.User;
import com.project.playit.Auth.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest request){
        User user = User.builder()
                .userID(UUID.randomUUID())
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailID(request.getEmailID())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return jwtService.generateToken(user);
    }

    @Override
    public String authenticate(AuthenticateRequest request) throws UsernameNotFoundException{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailID(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmailID(request.getEmailID())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return jwtService.generateToken(user);
    }
}
