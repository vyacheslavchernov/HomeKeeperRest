package com.vych.HomeKeeperRest.Domain.Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import com.vych.HomeKeeperRest.Domain.Views;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Данные о ролях пользователей
 */
@Entity(name = "authorities")
@Table(name = "authorities")
@Getter
@Setter
@Accessors(chain = true)
public class Role implements ResponsePayload {

    @Id
    @JsonProperty("username")
    private String username;

    @JsonProperty("authority")
    @JsonView(Views.AllData.class)
    private String authority;
}
