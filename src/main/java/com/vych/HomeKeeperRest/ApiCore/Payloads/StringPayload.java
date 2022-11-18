package com.vych.HomeKeeperRest.ApiCore.Payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Текстовая полезная нагрузка для ответа.
 */
public class StringPayload implements ResponsePayload {
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
