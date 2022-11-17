package com.vych.HomeKeeperRest.Repo;

import com.vych.HomeKeeperRest.Domain.Users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
