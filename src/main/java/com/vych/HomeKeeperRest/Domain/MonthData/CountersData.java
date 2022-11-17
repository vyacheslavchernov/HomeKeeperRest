package com.vych.HomeKeeperRest.Domain.MonthData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;

import javax.persistence.*;

/**
 * Данные о показаниях счётчиков за определённый месяц
 */
@Entity
@Table(name = "countersdata")
public class CountersData implements ResponsePayload {

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

    public long getHotwater() {
        return hotwater;
    }

    public Long getId() {
        return id;
    }

    public CountersData setHotwater(long hotwater) {
        this.hotwater = hotwater;
        return this;
    }

    public long getColdwater() {
        return coldwater;
    }

    public CountersData setColdwater(long coldwater) {
        this.coldwater = coldwater;
        return this;
    }

    public long getElectricity() {
        return electricity;
    }

    public CountersData setElectricity(long electricity) {
        this.electricity = electricity;
        return this;
    }
}
