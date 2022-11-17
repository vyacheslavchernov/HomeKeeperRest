package com.vych.HomeKeeperRest.Domain.MonthData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;

import javax.persistence.*;

/**
 * Дополнительные данные за определённый месяц.
 * Сюда включаются, например, платёж за интернет или любые вычеты из итоговой суммы(негативные значения)
 */
@Entity(name = "additionaldata")
@Table(name = "additionaldata")
public class AdditionalData implements ResponsePayload {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("description")
    private String description;

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public AdditionalData setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AdditionalData setDescription(String description) {
        this.description = description;
        return this;
    }
}
