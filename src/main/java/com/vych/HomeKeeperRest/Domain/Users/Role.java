package com.vych.HomeKeeperRest.Domain.Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import com.vych.HomeKeeperRest.Domain.Views;

import javax.persistence.*;

/**
 * Данные о ролях пользователей
 */
@Entity(name = "authorities")
@Table(name = "authorities")
public class Role implements ResponsePayload {

    @Id
    @JsonProperty("username")
    private String username;

    @JsonProperty("authority")
    @JsonView(Views.AllData.class)
    private String authority;

    public String getName() {
        return username;
    }

    public Role setName(String username) {
        this.username = username;
        return this;
    }

    public String getAuthority() {
        return authority;
    }

    public Role setAuthority(String authority) {
        this.authority = authority;
        return this;
    }
}
