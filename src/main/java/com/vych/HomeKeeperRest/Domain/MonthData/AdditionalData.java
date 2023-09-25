package com.vych.HomeKeeperRest.Domain.MonthData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Дополнительные данные за определённый месяц.
 * Сюда включаются, например, платёж за интернет или любые вычеты из итоговой суммы(негативные значения)
 */
@Entity(name = "additionaldata")
@Table(name = "additionaldata")
@Getter
@Setter
@Accessors(chain = true)
public class AdditionalData implements ResponsePayload {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("description")
    private String description;
}
