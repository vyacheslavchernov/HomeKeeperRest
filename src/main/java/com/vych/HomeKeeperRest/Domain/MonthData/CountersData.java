package com.vych.HomeKeeperRest.Domain.MonthData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Данные о показаниях счётчиков за определённый месяц
 */
@Entity
@Table(name = "countersdata")
@Getter
@Setter
@Accessors(chain = true)
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
}
