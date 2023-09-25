package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import com.vych.HomeKeeperRest.Domain.Views;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Базовый класс для ответов API.
 * Любые данные, которые передаются от API должны быть переданы этим классом.
 * Полезная нагрузка ответа обязательно должна имплементировать {@link ResponsePayload}.
 */
@Data
@Accessors(chain = true)
public class ApiResponse {
    @JsonProperty("status")
    @JsonView(Views.AllData.class)
    private Status status;

    @JsonProperty("payload")
    @JsonView(Views.AllData.class)
    private ResponsePayload payload;
}
