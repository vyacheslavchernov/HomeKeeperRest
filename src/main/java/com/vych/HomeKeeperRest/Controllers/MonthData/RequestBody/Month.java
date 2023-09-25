package com.vych.HomeKeeperRest.Controllers.MonthData.RequestBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Month {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("month")
    private Integer month;

    @JsonProperty("year")
    private Integer year;
}
