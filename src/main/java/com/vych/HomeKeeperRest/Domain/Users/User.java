package com.vych.HomeKeeperRest.Domain.Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;

import javax.persistence.*;

/**
 * Учётные данные пользователей
 */
@Entity(name = "users")
@Table(name = "users")
public class User implements ResponsePayload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("enabled")
    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorities_username", unique = true, nullable = false)
    @JsonProperty("role")
    private Role role;

    public Long getId() {
        return id;
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
