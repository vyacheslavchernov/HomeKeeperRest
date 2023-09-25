package com.vych.HomeKeeperRest.Controllers.Users.RequestBody;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegistrationData {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
