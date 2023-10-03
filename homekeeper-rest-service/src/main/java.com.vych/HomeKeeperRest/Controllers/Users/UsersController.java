package HomeKeeperRest.Controllers.Users;

import HomeKeeperRest.ApiCore.ApiResponse;
import HomeKeeperRest.ApiCore.Exceptions.DataException;
import HomeKeeperRest.ApiCore.Exceptions.IncorrectData;
import HomeKeeperRest.ApiCore.Payloads.ListPayload;
import HomeKeeperRest.ApiCore.ResponseUtil;
import HomeKeeperRest.ApiCore.Status;
import HomeKeeperRest.ApiCore.StatusCode;
import HomeKeeperRest.Controllers.Users.RequestBody.RegistrationData;
import HomeKeeperRest.Controllers.Users.RequestBody.UpdateData;
import HomeKeeperRest.Domain.Users.UserData;
import HomeKeeperRest.Repo.Users.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class UsersController {
    public static final String CONTROLLER_ENDPOINT = "api/users";

    //region Controller routes
    public static final String GET_USER = "/get";
    public static final String GET_ALL_USER = "/getall";
    public static final String SIGNUP = "/signup";
    public static final String DELETE = "/delete";
    public static final String UPDATE = "/update";
    //endregion Controller routes

    //region Repeatedly used prompts
    public static final String CANT_FIND_USER_DATA = "Не удалось получить данные пользователя";
    public static final String CANT_REG_NEW_USER = "Не удолось добавить пользователя";
    public static final String CANT_DELETE_USER = "Не удалось удалить пользователя";
    public static final String CANT_UPDATE_USER = "Не удалось обновить данные пользователя";
    //endregion Repeatedly used prompts

    @Autowired
    private UserDataRepo USER_DATA_REPO;
    @Autowired
    private UserDetailsManager userDetailsManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping(CONTROLLER_ENDPOINT + GET_USER)
    public ApiResponse getUser(@RequestParam String username) {
        if (username == null) {
            return ResponseUtil.buildError(
                    new IncorrectData("Не указан username искомого пользователя"),
                    CANT_FIND_USER_DATA
            );
        }

        User user;
        try {
            user = (User) userDetailsManager.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return ResponseUtil.buildError(
                    new IncorrectData("Не найдено пользователя с username " + username),
                    CANT_FIND_USER_DATA
            );
        }

        UserData data = USER_DATA_REPO.findById(user.getUsername()).orElse(null);
        return data == null
                ? ResponseUtil.buildError(new DataException("Не найдено записи с данными пользователя " + username), CANT_FIND_USER_DATA)
                : new ApiResponse().setStatus(new Status().setCode(StatusCode.SUCCESS)).setPayload(data);
    }

    @GetMapping(CONTROLLER_ENDPOINT + GET_ALL_USER)
    public ApiResponse getAllUsers() {
        ArrayList<UserData> usersData;

        try {
            usersData = (ArrayList<UserData>) USER_DATA_REPO.findAll();
        } catch (Exception e) {
            return ResponseUtil.buildError(e, "Ошибка при попытке получить всех пользователей");
        }

        return new ApiResponse()
                .setStatus(new Status().setCode(StatusCode.SUCCESS))
                .setPayload(new ListPayload().setListOfPayload(Collections.singletonList(usersData)));
    }

    @PostMapping(CONTROLLER_ENDPOINT + SIGNUP)
    public ApiResponse addUser(@RequestBody RegistrationData registrationData) {
        if (
                registrationData.getUsername() == null || registrationData.getUsername().equals("") ||
                        registrationData.getPassword() == null || registrationData.getPassword().equals("") ||
                        registrationData.getFirstName() == null || registrationData.getFirstName().equals("") ||
                        registrationData.getLastName() == null || registrationData.getLastName().equals("")
        ) {
            return ResponseUtil.buildError(
                    new IncorrectData("Некорректные регистрационные данные пользователя"),
                    CANT_REG_NEW_USER
            );
        }

        try {
            userDetailsManager.loadUserByUsername(registrationData.getUsername());
        } catch (UsernameNotFoundException e) {
            UserDetails user = User.builder()
                    .username(registrationData.getUsername())
                    .password(passwordEncoder.encode(registrationData.getPassword()))
                    .roles("USER")
                    .build();

            UserData userData = new UserData()
                    .setUsername(registrationData.getUsername())
                    .setFirstName(registrationData.getFirstName())
                    .setLastName(registrationData.getLastName());

            USER_DATA_REPO.save(userData);
            userDetailsManager.createUser(user);

            return ResponseUtil.buildSuccess();
        }

        return ResponseUtil.buildError(
                new IncorrectData("Пользователь с username " + registrationData.getUsername() + " уже существует"),
                CANT_REG_NEW_USER
        );
    }

    @PostMapping(CONTROLLER_ENDPOINT + DELETE)
    public ApiResponse deleteUser(@RequestParam String username) {
        try {
            userDetailsManager.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return ResponseUtil.buildError(
                    new IncorrectData("Пользователя с username " + username + " не существует"),
                    CANT_DELETE_USER
            );
        }

        try {
            userDetailsManager.deleteUser(username);
            USER_DATA_REPO.deleteById(username);
        } catch (Exception e) {
            return ResponseUtil.buildError(e, CANT_DELETE_USER);
        }

        return ResponseUtil.buildSuccess();
    }

    @PostMapping(CONTROLLER_ENDPOINT + UPDATE)
    public ApiResponse updateUser(@RequestBody UpdateData updateData) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsManager.loadUserByUsername(updateData.getUsername());
        } catch (UsernameNotFoundException e) {
            return ResponseUtil.buildError(
                    new IncorrectData("Пользователя с username " + updateData.getUsername() + " не существует"),
                    CANT_UPDATE_USER
            );
        }

        // Try update psw
        if (updateData.getPassword() != null) {
            if (updateData.getPassword().equals("")) {
                return ResponseUtil.buildError(
                        new IncorrectData("Пароль не может быть пустой строкой"),
                        CANT_UPDATE_USER
                );
            }

            try {
                User oldUser = (User) userDetailsManager.loadUserByUsername(updateData.getUsername());
                if (!passwordEncoder.matches(updateData.getOldPassword(), oldUser.getPassword())) {
                    return ResponseUtil.buildError(
                            new IncorrectData("Текущий пароль введён неверно"),
                            CANT_UPDATE_USER
                    );
                }

                UserDetails user = User.builder()
                        .username(updateData.getUsername())
                        .password(passwordEncoder.encode(updateData.getPassword()))
                        .authorities(oldUser.getAuthorities())
                        .build();

                userDetailsManager.updateUser(user);
            } catch (Exception e) {
                return ResponseUtil.buildError(
                        e,
                        CANT_UPDATE_USER
                );
            }
        }

        // Try update user data
        UserData userData = USER_DATA_REPO.findById(updateData.getUsername()).orElse(null);

        if (userData == null) {
            return ResponseUtil.buildError(
                    new DataException("Не найдено записи о данных пользователя"),
                    CANT_UPDATE_USER
            );
        }

        if (!updateData.getFirstName().equals("")) {
            userData.setFirstName(updateData.getFirstName());
        }
        if (!updateData.getLastName().equals("")) {
            userData.setLastName(updateData.getLastName());
        }

        try {
            USER_DATA_REPO.save(userData);
        } catch (Exception e) {
            return ResponseUtil.buildError(
                    e,
                    CANT_UPDATE_USER
            );
        }

        return ResponseUtil.buildSuccess();
    }

}
