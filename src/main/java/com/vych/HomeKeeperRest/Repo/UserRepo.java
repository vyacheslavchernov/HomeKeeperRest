package com.vych.HomeKeeperRest.Repo;

import com.vych.HomeKeeperRest.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
