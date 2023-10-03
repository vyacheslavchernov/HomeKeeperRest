package HomeKeeperRest.Repo.Users;

import HomeKeeperRest.Domain.Users.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepo extends JpaRepository<UserData, String> {
}
