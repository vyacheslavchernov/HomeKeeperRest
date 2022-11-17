package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;

/**
 * Базовый класс для ответов API.
 * Любые данные, которые передаются от API должны быть переданы этим классом.
 * Полезная нагрузка ответа обязательно должна имплементировать {@link ResponsePayload}.
 */
public class ApiResponse {

    @JsonProperty("status")
    private Status status;

    @JsonProperty("payload")
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
