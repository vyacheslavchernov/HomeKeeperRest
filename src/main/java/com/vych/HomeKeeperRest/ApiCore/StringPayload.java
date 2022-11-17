package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Базовая полезная нагрузка для ответа.
 */
public class StringPayload implements ResponsePayload{
    @JsonProperty("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public StringPayload setMessage(String message) {
        this.message = message;
        return this;
    }
}
