package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.Domain.Views;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс-обёртка ошибок.
 */
@Data
@Accessors(chain = true)
public class Error {
    @JsonProperty("description")
    @JsonView(Views.AllData.class)
    private String description;

    @JsonProperty("trace")
    @JsonView(Views.AllData.class)
    private String trace;
}
