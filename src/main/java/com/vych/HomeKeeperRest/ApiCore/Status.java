package com.vych.HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.Domain.Views;

import java.util.ArrayList;
import java.util.List;


/**
 * Статус выполнения запроса.
 * Значения для {@link #code} брать из {@link StatusCode}
 */
public class Status {

    @JsonProperty("code")
    @JsonView(Views.AllData.class)
    private int code;

    @JsonProperty("errors")
    @JsonView(Views.AllData.class)
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

    public Status addError(Error error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }

        this.errors.add(error);

        return this;
    }
}
