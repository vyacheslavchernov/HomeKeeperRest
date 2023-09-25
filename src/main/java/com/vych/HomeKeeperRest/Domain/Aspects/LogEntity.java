package com.vych.HomeKeeperRest.Domain.Aspects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "logs")
@Getter
@Setter
@Accessors(chain = true)
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
}
