package com.vych.HomeKeeperRest.Domain.Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import com.vych.HomeKeeperRest.Domain.Views;

import javax.persistence.*;

/**
 * Учётные данные пользователей
 */
@Entity
@Table(name = "users")
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

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }
}
