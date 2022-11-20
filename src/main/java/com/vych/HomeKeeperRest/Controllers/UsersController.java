package com.vych.HomeKeeperRest.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.ApiCore.ApiResponse;
import com.vych.HomeKeeperRest.ApiCore.Exceptions.IncorrectData;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ListPayload;
import com.vych.HomeKeeperRest.ApiCore.ResponseUtil;
import com.vych.HomeKeeperRest.ApiCore.Status;
import com.vych.HomeKeeperRest.ApiCore.StatusCode;
import com.vych.HomeKeeperRest.Aspects.Annotations.NeedLogs;
import com.vych.HomeKeeperRest.Domain.Users.User;
import com.vych.HomeKeeperRest.Domain.Views;
import com.vych.HomeKeeperRest.Repo.Users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class UsersController {

    private final String CONTROLLER_ENDPOINT = "api/users/";

    private final UserRepo USER_REPO;

    @Autowired
    public UsersController(UserRepo userRepo) {
        this.USER_REPO = userRepo;
    }

    @NeedLogs
    @JsonView(Views.NonSensitiveData.class)
    @GetMapping(CONTROLLER_ENDPOINT + "get")
    public ApiResponse getUser(@RequestParam String username) {
        if (username == null) {
            return ResponseUtil.buildError(
                    new IncorrectData("Не указан username искомого пользователя"),
                    "Не удалось получить данные пользователя"
            );
        }

        User user = USER_REPO.findByUsername(username).orElse(null);
        if (user == null ) {
            return ResponseUtil.buildError(
                    new IncorrectData("Не найдено пользователя с username " + username),
                    "Не удалось получить данные пользователя"
            );
        } else {
            return new ApiResponse().setStatus(new Status().setCode(StatusCode.SUCCESS)).setPayload(user);
        }
    }

    @NeedLogs
    @JsonView(Views.NonSensitiveData.class)
    @GetMapping(CONTROLLER_ENDPOINT + "getall")
    public ApiResponse getAllUsers() {
        ArrayList<User> users;

        try {
            users = (ArrayList<User>) USER_REPO.findAll();
        } catch (Exception e) {
            return ResponseUtil.buildError(e, "Ошибка при попытке получить всех пользователей");
        }

        return new ApiResponse()
                .setStatus(new Status().setCode(StatusCode.SUCCESS))
                .setPayload(new ListPayload().setListOfPayload(Collections.singletonList(users)));
    }

    @NeedLogs
    @PostMapping(CONTROLLER_ENDPOINT + "add")
    public ApiResponse addUser(@RequestBody User user) {
        if (USER_REPO.findByUsername(user.getUsername()).orElse(null) != null) {
            return ResponseUtil.buildError(
                    new IncorrectData("Пользователь с username " + user.getUsername() + " уже существует"),
                    "Не удалось добавить нового пользователя"
            );
        }

        try {
            USER_REPO.save(user);
        } catch (Exception e) {
            return ResponseUtil.buildError(e, "Ошибка при попытке добавить пользователя");
        }

        return ResponseUtil.buildSuccess();
    }

    @NeedLogs
    @PostMapping(CONTROLLER_ENDPOINT + "delete")
    public ApiResponse deleteUser(@RequestParam String username) {
        User user = USER_REPO.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseUtil.buildError(
                    new IncorrectData("Пользователя с username " + username + " не существует"),
                    "Не удалось удалить пользователя"
            );
        }

        try {
            USER_REPO.delete(user);
        } catch (Exception e) {
            return ResponseUtil.buildError(e, "Ошибка при попытке удалить пользователя");
        }

        return ResponseUtil.buildSuccess();
    }

    @NeedLogs
    @PostMapping(CONTROLLER_ENDPOINT + "update")
    public ApiResponse updateUser(@RequestBody User userdata) {
        User user = USER_REPO.findByUsername(userdata.getUsername()).orElse(null);
        if (user == null) {
            return ResponseUtil.buildError(
                    new IncorrectData("Пользователя с username " + userdata.getUsername() + " не существует"),
                    "Не удалось удалить пользователя"
            );
        }


        userdata
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .getRole().setName(user.getUsername());

        try {
            USER_REPO.save(userdata);
        } catch (Exception e) {
            return ResponseUtil.buildError(e, "Ошибка при попытке удалить пользователя");
        }

        return ResponseUtil.buildSuccess();
    }

}
