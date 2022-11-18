package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.Domain.Views;

/**
 * Класс-обёртка ошибок.
 */
public class Error {

    @JsonProperty("description")
    @JsonView(Views.AllData.class)
    private String description;

    @JsonProperty("trace")
    @JsonView(Views.AllData.class)
    private String trace;

    public String getDescription() {
        return description;
    }

    public Error setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTrace() {
        return trace;
    }

    public Error setTrace(String trace) {
        this.trace = trace;
        return this;
    }
}
