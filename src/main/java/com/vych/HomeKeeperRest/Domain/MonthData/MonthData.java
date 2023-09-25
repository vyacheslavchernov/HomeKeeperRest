package com.vych.HomeKeeperRest.Domain.MonthData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * Данные за месяц
 */
@Entity
@Table(name = "monthdata")
@Getter
@Setter
@Accessors(chain = true)
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
}
