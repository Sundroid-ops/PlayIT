package com.project.playit.Auth.Service;

import com.project.playit.Auth.Entity.User.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface CurrentUserService {
    public User getCurrentUser();

    public void setCurrentUser(UserDetails userDetails);
}
