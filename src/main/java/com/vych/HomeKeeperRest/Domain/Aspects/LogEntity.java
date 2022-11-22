package com.vych.HomeKeeperRest.Domain.Aspects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;

import javax.persistence.*;

@Entity
@Table(name = "logs")
public class LogEntity implements ResponsePayload {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("session")
    private String session;

    @JsonProperty("username")
    private String username;

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("timestamp")
    private String timestamp;

    @Column(length = 10000)
    @JsonProperty("requesBody")
    private String requestBody;

    @Column(length = 10000)
    @JsonProperty("response")
    private String response;

    public Long getId() {
        return id;
    }

    public LogEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSession() {
        return session;
    }

    public LogEntity setSession(String session) {
        this.session = session;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LogEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public LogEntity setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LogEntity setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public LogEntity setRequestBody(String requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public LogEntity setResponse(String response) {
        this.response = response;
        return this;
    }
}
