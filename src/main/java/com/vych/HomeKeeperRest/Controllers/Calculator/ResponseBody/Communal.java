package com.vych.HomeKeeperRest.Controllers.Calculator.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;

public class Communal implements ResponsePayload {

    @JsonProperty("hotwater")
    private double hotwater;

    @JsonProperty("coldwater")
    private double coldwater;

    @JsonProperty("drainage")
    private double drainage;

    @JsonProperty("electricity")
    private double electricity;

    @JsonProperty("total")
    private double total;

    public double getHotwater() {
        return hotwater;
    }

    public Communal setHotwater(double hotwater) {
        this.hotwater = hotwater;
        return this;
    }

    public double getColdwater() {
        return coldwater;
    }

    public Communal setColdwater(double coldwater) {
        this.coldwater = coldwater;
        return this;
    }

    public double getDrainage() {
        return drainage;
    }

    public Communal setDrainage(double drainage) {
        this.drainage = drainage;
        return this;
    }

    public double getElectricity() {
        return electricity;
    }

    public Communal setElectricity(double electricity) {
        this.electricity = electricity;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public Communal setTotal(double total) {
        this.total = total;
        return this;
    }

    public Communal calcTotal() {
        this.total = this.coldwater + this.hotwater + this.drainage + this.electricity;
        return this;
    }
}
