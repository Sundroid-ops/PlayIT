package com.project.playit.Auth.Service;

import com.project.playit.Auth.Entity.User.User;
import com.project.playit.Auth.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Autowired
    private UserRepository userRepository;

    private User currentUser;

    @Override
    public User getCurrentUser(){
        return currentUser;
    }

    @Override
    public void setCurrentUser(UserDetails userDetails) {
        currentUser = userRepository.findByEmailID(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
