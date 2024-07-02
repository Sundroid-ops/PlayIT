package com.project.playit.Auth.Entity.User;

import com.project.playit.project.Entity.Song;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "_user")
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class User implements UserDetails {

    @Id
    private UUID userID;

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

    @Enumerated(EnumType.STRING)
    private Role role;

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
        return emailID;
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
