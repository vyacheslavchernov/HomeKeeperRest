package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import com.vych.HomeKeeperRest.Domain.Views;

/**
 * Базовый класс для ответов API.
 * Любые данные, которые передаются от API должны быть переданы этим классом.
 * Полезная нагрузка ответа обязательно должна имплементировать {@link ResponsePayload}.
 */
public class ApiResponse {

    @JsonProperty("status")
    @JsonView(Views.AllData.class)
    private Status status;

    @JsonProperty("payload")
    @JsonView(Views.AllData.class)
    private ResponsePayload payload;

    public Status getStatus() {
        return status;
    }

    public ApiResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public ResponsePayload getPayload() {
        return payload;
    }

    public ApiResponse setPayload(ResponsePayload payload) {
        this.payload = payload;
        return this;
    }
}
