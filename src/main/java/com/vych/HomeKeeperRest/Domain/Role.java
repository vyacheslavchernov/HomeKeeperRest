package com.vych.HomeKeeperRest.Domain;

import com.vych.HomeKeeperRest.ApiCore.ResponsePayload;

import javax.persistence.*;

/**
 * Данные о ролях пользователей
 */
@Entity(name = "authorities")
@Table(name = "authorities")
public class Role implements ResponsePayload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String authority;


    public Long getId() {
        return id;
    }

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
