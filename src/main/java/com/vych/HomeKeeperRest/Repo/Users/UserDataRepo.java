package com.vych.HomeKeeperRest.Repo.Users;

import com.vych.HomeKeeperRest.Domain.Users.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepo extends JpaRepository<UserData, String> {
}
