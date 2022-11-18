package com.vych.HomeKeeperRest.Repo.Users;

import com.vych.HomeKeeperRest.Domain.Users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
