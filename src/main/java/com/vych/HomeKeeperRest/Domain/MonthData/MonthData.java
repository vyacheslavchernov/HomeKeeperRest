package com.vych.HomeKeeperRest.Domain.MonthData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;

import javax.persistence.*;
import java.util.List;

/**
 * Данные за месяц
 */
@Entity
@Table(name = "monthdata")
public class MonthData implements ResponsePayload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("housemates")
    private int housemates;

    @JsonProperty("rent")
    private double rent;

    @JsonProperty("month")
    private int month;

    @JsonProperty("year")
    private int year;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countersdata_id", unique = true, nullable = false)
    @JsonProperty("countersData")
    private CountersData countersData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tariffsdata_id", unique = true, nullable = false)
    @JsonProperty("tariffsData")
    private TariffsData tariffsData;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "additionaldata_id", nullable = false)
    @JsonProperty("additionalData")
    private List<AdditionalData> additionalData;

    public Long getId() {
        return id;
    }

    public int getHousemates() {
        return housemates;
    }

    public MonthData setHousemates(int housemates) {
        this.housemates = housemates;
        return this;
    }

    public double getRent() {
        return rent;
    }

    public MonthData setRent(double rent) {
        this.rent = rent;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public MonthData setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getYear() {
        return year;
    }

    public MonthData setYear(int year) {
        this.year = year;
        return this;
    }

    public CountersData getCountersData() {
        return countersData;
    }

    public MonthData setCountersData(CountersData countersData) {
        this.countersData = countersData;
        return this;
    }

    public TariffsData getTariffsData() {
        return tariffsData;
    }

    public MonthData setTariffsData(TariffsData tariffsData) {
        this.tariffsData = tariffsData;
        return this;
    }

    public List<AdditionalData> getAdditionalData() {
        return additionalData;
    }

    public MonthData setAdditionalData(List<AdditionalData> additionalData) {
        this.additionalData = additionalData;
        return this;
    }
}
