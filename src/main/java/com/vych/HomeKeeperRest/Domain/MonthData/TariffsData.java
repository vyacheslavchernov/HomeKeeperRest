package com.vych.HomeKeeperRest.Domain.MonthData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;

import javax.persistence.*;

/**
 * Данные о тарифах на определённый месяц
 */
@Entity
@Table(name = "tariffsdata")
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

    public Long getId() {
        return id;
    }

    public long getHotwater() {
        return hotwater;
    }

    public TariffsData setHotwater(long hotwater) {
        this.hotwater = hotwater;
        return this;
    }

    public long getColdwater() {
        return coldwater;
    }

    public TariffsData setColdwater(long coldwater) {
        this.coldwater = coldwater;
        return this;
    }

    public long getElectricity() {
        return electricity;
    }

    public TariffsData setElectricity(long electricity) {
        this.electricity = electricity;
        return this;
    }

    public long getDrainage() {
        return drainage;
    }

    public TariffsData setDrainage(long drainage) {
        this.drainage = drainage;
        return this;
    }
}
