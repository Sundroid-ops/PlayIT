package com.project.playit.Auth.Controller;

import lombok.Data;

@Data
public class RegisterRequest {
    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
