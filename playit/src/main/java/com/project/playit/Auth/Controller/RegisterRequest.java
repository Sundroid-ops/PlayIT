package com.project.playit.Auth.Controller;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class RegisterRequest {

    @NotBlank(message = "UserName cannot be empty")
    @Column(unique = true)
    @Length(min = 3, max = 15, message = "UserName should have more than 2 and less than 16 character")
    @NotNull(message = "UserName is required")
    private String userName;

    @Length(min = 3, max = 15, message = "FirstName should have more than 2 and less than 16 character")
    private String firstName;

    @Length(min = 3, max = 15, message = "LastName should have more than 2 and less than 16 character")
    private String lastName;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String emailID;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
