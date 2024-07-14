package com.project.playit.Auth.Repository;

import com.project.playit.Auth.Entity.User.Role;
import com.project.playit.Auth.Entity.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userID(UUID.randomUUID())
                .userName("sam123")
                .firstName("sam")
                .lastName("kaur")
                .emailID("sam@gmail.com")
                .password("123456")
                .role(Role.USER)
                .build();

        entityManager.persist(user);
    }

    @Test
    void findUserByValidEmailID() {
        User fetchUser = userRepository.findByEmailID("sam@gmail.com").get();
        assertEquals(fetchUser.getUsername(), user.getUsername());
    }

    @Test
    void CheckNotRegisteredEmailID(){
        Optional<User> fetchUser = userRepository.findByEmailID("sam");
        assertEquals(Optional.empty(), fetchUser);
    }
}