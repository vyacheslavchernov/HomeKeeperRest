package com.vych.HomeKeeperRest.Domain;

import javax.persistence.*;

@Entity(name = "authorities")
@Table(name = "authorities")
public class Role {

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
