package HomeKeeperRest.Domain.Users;

import HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class UserData implements ResponsePayload {
    @Id
    private String username;
    private String firstName;
    private String lastName;
}
