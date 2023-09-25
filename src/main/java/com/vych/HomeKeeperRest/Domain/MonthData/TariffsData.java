package com.vych.HomeKeeperRest.Domain.MonthData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Данные о тарифах на определённый месяц
 */
@Entity
@Table(name = "tariffsdata")
@Getter
@Setter
@Accessors(chain = true)
public class TariffsData implements ResponsePayload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("hotwater")
    private long hotwater;

    @JsonProperty("coldwater")
    private long coldwater;

    @JsonProperty("electricity")
    private long electricity;

    @JsonProperty("drainage")
    private long drainage;
}
