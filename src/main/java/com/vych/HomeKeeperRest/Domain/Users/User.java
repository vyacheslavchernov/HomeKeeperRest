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
 * Учётные данные пользователей
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class User implements ResponsePayload {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.NonSensitiveData.class)
    private Long id;

    @JsonProperty("firstName")
    @JsonView(Views.NonSensitiveData.class)
    private String firstName;

    @JsonProperty("lastName")
    @JsonView(Views.NonSensitiveData.class)
    private String lastName;

    @JsonProperty("email")
    @JsonView(Views.NonSensitiveData.class)
    private String email;

    @JsonProperty("username")
    @JsonView(Views.NonSensitiveData.class)
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("enabled")
    @JsonView(Views.NonSensitiveData.class)
    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorities_username", unique = true, nullable = false)
    @JsonProperty("role")
    @JsonView(Views.NonSensitiveData.class)
    private Role role;
}
