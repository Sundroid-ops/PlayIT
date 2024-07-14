package com.project.playit.Auth.Controller;

import com.project.playit.Auth.DTO.RegisterRequest;
import com.project.playit.Auth.Filter.JwtAuthFilter;
import com.project.playit.Auth.Service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private WebApplicationContext context;

    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();

        registerRequest.setUserName("sam123");
        registerRequest.setFirstName("sam");
        registerRequest.setLastName("kaur");
        registerRequest.setEmailID("sam@gmail.com");
        registerRequest.setPassword("123456");

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new JwtAuthFilter())
                .build();
    }

    @Test
    void register() throws Exception {
        Map<String, String> jwtToken = new HashMap<>();
        jwtToken.put("jwt", "token");

        Mockito.when(authService.register(registerRequest))
                .thenReturn(jwtToken);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userName\": \"sam123\",\n" +
                        "    \"firstName\": \"sam\",\n" +
                        "    \"lastName\": \"kaur\",\n" +
                        "    \"emailID\": \"sam@gmail.com\",\n" +
                        "    \"password\": \"123456\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt")
                        .value("token"));
    }

    @Test
    void authenticate() {
    }
}