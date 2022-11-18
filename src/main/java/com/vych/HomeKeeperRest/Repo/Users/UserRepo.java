package com.vych.HomeKeeperRest.Repo.Users;

import com.vych.HomeKeeperRest.Domain.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
