package com.vych.HomeKeeperRest.Controllers.Calculator.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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

    public Communal calcTotal() {
        this.total = this.coldwater + this.hotwater + this.drainage + this.electricity;
        return this;
    }
}
