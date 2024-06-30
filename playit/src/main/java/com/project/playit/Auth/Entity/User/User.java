package com.project.playit.Auth.Entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    private UUID ID;

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
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Length(min = 5, max = 15, message = "Password should have more than 4 and less than 16 character")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
