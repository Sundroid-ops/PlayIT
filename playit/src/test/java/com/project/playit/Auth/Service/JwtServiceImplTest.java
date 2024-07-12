package com.project.playit.Auth.Service;

import com.project.playit.Auth.Entity.User.Role;
import com.project.playit.Auth.Entity.User.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceImplTest {
    @Mock
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private String jwt;

    private final static String secret_key =
            "B374A26A71490437AA024E4FADD5B497FDFF1A8EA6FF12F6FB65AF2720B59CCF";

    private Key key;

    private static final String encodedPassword = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

    private static final String password = "123456";

    private User user;

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

        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret_key));

        jwt = Jwts.builder()
                .setSubject(user.getEmailID())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    void extractUserName(){

    }

    @Test
    void extractClaim() {
    }

    @Test
    void isTokenValid() {
    }

    @Test
    void isTokenExpiration() {
    }

    @Test
    void generateToken(){
        Mockito.when(jwtService.generateToken(user))
                .thenReturn(jwt);

        String jwts = jwtService.generateToken(user);
        assertNotNull(jwts);
        assertEquals(jwts, jwt);
    }

    @Test
    void getSignInKey() {
        assertNotNull(key);
    }

    @Test
    void extractAllClaims() {

    }
}