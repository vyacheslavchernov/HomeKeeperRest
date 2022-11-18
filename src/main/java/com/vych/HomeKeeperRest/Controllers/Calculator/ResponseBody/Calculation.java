package com.vych.HomeKeeperRest.Controllers.Calculator.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;

public class Calculation implements ResponsePayload {

    @JsonProperty("totalWithCommunal")
    private double totalWithCommunal;

    @JsonProperty("totalWithCommunalAndAdditional")
    private double totalWithCommunalAndAdditional;

    @JsonProperty("totalWithCommunalAndNegativeAdditional")
    private double totalWithCommunalAndNegativeAdditional;

    @JsonProperty("communal")
    private Communal communal;

    public double getTotalWithCommunal() {
        return totalWithCommunal;
    }

    public Calculation setTotalWithCommunal(double totalWithCommunal) {
        this.totalWithCommunal = totalWithCommunal;
        return this;
    }

    public double getTotalWithCommunalAndAdditional() {
        return totalWithCommunalAndAdditional;
    }

    public Calculation setTotalWithCommunalAndAdditional(double totalWithCommunalAndAdditional) {
        this.totalWithCommunalAndAdditional = totalWithCommunalAndAdditional;
        return this;
    }

    public double getTotalWithCommunalAndNegativeAdditional() {
        return totalWithCommunalAndNegativeAdditional;
    }

    public Calculation setTotalWithCommunalAndNegativeAdditional(double totalWithCommunalAndNegativeAdditional) {
        this.totalWithCommunalAndNegativeAdditional = totalWithCommunalAndNegativeAdditional;
        return this;
    }

    public Communal getCommunal() {
        return communal;
    }

    public Calculation setCommunal(Communal communal) {
        this.communal = communal;
        return this;
    }
}
