package com.project.playit.Auth.Service;

import com.project.playit.Auth.Entity.User.Role;
import com.project.playit.Auth.Entity.User.User;
import com.project.playit.Auth.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplTest {

    @Mock
    private AuthService authService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    private static final String password = "123456";
    private static final String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
            ".eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ" +
            ".pF3q46_CLIyP_1QZPpeccbs-hC4n9YW2VMBjKrSO6Wg";

    private static final String encodedPassword = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

    @BeforeEach
    void setUp() {
        Mockito.when(passwordEncoder.encode(password))
                .thenReturn(encodedPassword);

        user = User.builder()
                .userID(UUID.randomUUID())
                .userName("samKaur123")
                .firstName("sam")
                .lastName("kaur")
                .emailID("sam@gmail.com")
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();

    }

    @Test
    void register() {
        Mockito.when(userRepository.save(user))
                .thenReturn(user);

        User savedUser = userRepository.save(user);

        assertEquals(savedUser, user);

        Mockito.when(jwtService.generateToken(user))
                .thenReturn(jwt);

        String jwtToken = jwtService.generateToken(user);

        assertEquals(jwtToken, jwt);
    }

    @Test
    void authenticate() {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        user.getEmailID(),
                        password
                );

        assertEquals(authenticationToken.getName(), user.getEmailID());

        Mockito.when(userRepository.findByEmailID(user.getEmailID()))
                .thenReturn(Optional.ofNullable(user));

        User fetchUser = userRepository.findByEmailID(user.getEmailID()).get();

        assertEquals(fetchUser.getUserID(), user.getUserID());
        assertEquals(fetchUser.getEmailID(), user.getEmailID());

        UsernameNotFoundException usernameNotFoundException =
                assertThrows(UsernameNotFoundException.class, () -> {
                    userRepository.findByEmailID("notFound@gmail.com")
                            .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
                });

        assertEquals(usernameNotFoundException.getLocalizedMessage(), "User Not Found");
    }
}