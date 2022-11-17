package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс-обёртка ошибок.
 */
public class Error {

    @JsonProperty("description")
    private String description;

    @JsonProperty("trace")
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
