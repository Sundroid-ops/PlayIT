package com.project.playit.Auth.Controller;

import lombok.Data;

@Data
public class AuthenticateRequest {
    private String emailID;

    private String password;
}
