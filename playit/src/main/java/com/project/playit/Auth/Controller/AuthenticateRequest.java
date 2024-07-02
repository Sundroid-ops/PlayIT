package com.project.playit.Auth.Controller;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class AuthenticateRequest {

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String emailID;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
