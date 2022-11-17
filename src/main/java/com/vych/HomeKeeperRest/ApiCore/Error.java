package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс-обёртка ошибок.
 */
public class Error {

    @JsonProperty("description")
    private String description;

    @JsonProperty("stack")
    private String stack;
}
