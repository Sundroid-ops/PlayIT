package com.project.playit.Auth.Repository;

import com.project.playit.Auth.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailID(String emailID);
}
