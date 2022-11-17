package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * Статус выполнения запроса.
 * Значения для {@link #code} брать из {@link StatusCode}
 */
public class Status {

    @JsonProperty("code")
    private int code;

    @JsonProperty("errors")
    private List<Error> errors;

    public int getCode() {
        return code;
    }

    public Status setCode(int code) {
        this.code = code;
        return this;
    }

    public Status setCode(StatusCode code) {
        this.code = code.ordinal();
        return this;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public Status setErrors(List<Error> errors) {
        this.errors = errors;
        return this;
    }
}
