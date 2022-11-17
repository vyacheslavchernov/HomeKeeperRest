package com.vych.HomeKeeperRest.Controllers.MonthData.RequestBody;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Month {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("month")
    private Integer month;

    @JsonProperty("year")
    private Integer year;

    public Long getId() {
        return id;
    }

    public Month setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getMonth() {
        return month;
    }

    public Month setMonth(Integer month) {
        this.month = month;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public Month setYear(Integer year) {
        this.year = year;
        return this;
    }
}
